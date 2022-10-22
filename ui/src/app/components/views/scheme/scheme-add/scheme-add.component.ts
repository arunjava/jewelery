import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../../../service/customer-service/customer.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NgbDateStruct, NgbDateAdapter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { Scheme } from '../../../../models/scheme/Scheme.model';
import { UOM } from '../../../../models/uom/UOM.model';
import { SchemeService } from 'src/app/service/scheme/scheme.service';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from '../../../helper/modal/modal.component';
import { OfferServiceService } from 'src/app/service/offer-service/offer-service.service';
import { UomService } from 'src/app/service/uom/uom.service';
import { Offer } from 'src/app/models/offers/Offer.model';
import { ProductCategory } from 'src/app/models/product/ProductCategory.model';
import { ProductServiceService } from 'src/app/service/product-service/product-service.service';

@Component({
  selector: 'app-scheme-add',
  templateUrl: './scheme-add.component.html',
  styleUrls: ['./scheme-add.component.css']
})
export class SchemeAddComponent implements OnInit {

  scheme = {} as Scheme;
  offers = {} as Array<Offer>;
  selectedProdCategory = {} as ProductCategory;
  prodCategories = [] as ProductCategory[];
  selectedOffers = [] as Offer[];
  selectedOffer = {} as Offer;
  customerID: number = 0;
  loading: boolean = false;
  uoms = {} as Array<UOM>;
  currentDate: Date = new Date();
  selectedUOMType = {} as UOM;
  startDate: NgbDateStruct = {
    "year": this.currentDate.getUTCFullYear(),
    "month": this.currentDate.getUTCMonth() + 1,
    "day": this.currentDate.getDate()
  };
  endDate: NgbDateStruct = {
    "year": 2099,
    "month": 12,
    "day": 31
  };

  constructor(
    private _route: ActivatedRoute,
    private customerServie: CustomerService,
    private formBuilder: FormBuilder,
    private schmeService: SchemeService,
    private modalService: BsModalService,
    private ngbCalendar: NgbCalendar,
    private dateAdapter: NgbDateAdapter<string>,
    private offerService: OfferServiceService,
    private uomService: UomService,
    private productService: ProductServiceService
  ) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.customerID = params['id'];
      console.log(this.customerID);
    });

    this.uomService.getAllActiveUOMSBsdOnCategoryID(4).subscribe((response) => {
      if (response.statusCode == 302) {
        this.uoms = response.result;
      } else {
        console.log(response.message);
      }
    }, (error) => {
      console.log(error.statusText);
    });

    this.offerService.getAllOffer().subscribe(response => {
      if (response.statusCode == 302) {
        this.offers = response.result;
      } else {
        console.log(response.message);
      }
    }, error => {
      console.log(error.statusText);
    });

    this.productService.getProductCategories().subscribe(response => {
      if(response.statusCode == 302) {
        this.prodCategories = response.result;
      } else {
        console.log(response.message);
      }
    } , error => {
      console.log(error.statusText);
    })

  }

  schemeForm = this.formBuilder.group({
    schemeName: ['', Validators.required],
    description: ['', Validators.required],
    uomType: ['', Validators.required],
    uomValue: ['', Validators.required],
    offerType: ['', Validators.required],
    prodCategory: ['', Validators.required]
  });

  // convenience getter for easy access to form fields
  get f() { return this.schemeForm.controls; }

  onFormSubmit() {
    console.log('Submit button clicked --> ' + JSON.stringify(this.schemeForm.value));
    this.loading = true;
    this.scheme.scheme_active = true;
    this.scheme.scheme_desc = this.schemeForm.value.description;
    this.scheme.scheme_name = this.schemeForm.value.schemeName;
    this.scheme.duration = this.schemeForm.value.uomValue;
    this.scheme.uomID = this.selectedUOMType.uom_id;
    this.scheme.offers = this.selectedOffers;
    this.scheme.prodCategory = this.selectedProdCategory;
    // this.scheme.beginDate = new Date(this.schemeForm.value.startDate.year, this.schemeForm.value.startDate.month, this.schemeForm.value.startDate.date);
    if(this.scheme.offers.length == 0) {
        this.loading = false;
        this.showmodel('Select atleast 1 Offer', 'Offer Not Selected');
    } else {
      this.schmeService.saveScheme(this.scheme).subscribe(
        (response) => {
          this.resetForm();
          this.loading = false;
          if (response.statusCode == 201) {
            this.showmodel('Scheme created!', 'Success');
          } else {
            console.log(response.message);
            this.showmodel(response.message, 'Error');
          }
        },
        (error) => {
          this.showmodel(error.statusText, 'Error');
        }
      )
    }
  }

  resetForm() {
    this.schemeForm.reset();
    this.selectedOffers = [];
  }

  showmodel(message: string, title: string) {
    console.log('Inside modal dispaly');
    console.log(message);
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = title;
    modalRef.content.message = message;
  }

  addOffer() {
    console.log(this.selectedOffer);
    let valueAlreadyExist = false;
    this.selectedOffers.forEach(offer => {
      if (offer.offerId == this.selectedOffer.offerId) {
        valueAlreadyExist = true;
      }
    });

    if (!valueAlreadyExist) {
      this.selectedOffers.push(this.selectedOffer);
    } else {
      this.showmodel('Offer already added', 'Alert!');
    }

    console.log(JSON.stringify(this.selectedOffers));
  }

  removeOffer(offerId: number) {
    for (let i = 0; i < this.selectedOffers.length; i++) {
      if(this.selectedOffers[i].offerId == offerId) {
        this.selectedOffers.splice(i, 1);
      }
    }
  }
}
