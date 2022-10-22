import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from 'src/app/components/helper/modal/modal.component';
import { Offer } from 'src/app/models/offers/Offer.model';
import { OfferServiceService } from 'src/app/service/offer-service/offer-service.service';

@Component({
  selector: 'app-offers-add',
  templateUrl: './offers-add.component.html',
  styleUrls: ['./offers-add.component.css']
})
export class OffersAddComponent implements OnInit {

  public loading = false;
  public offerForm = {} as FormGroup;
  public offerApplicableOn = [] as Array<string>;

  constructor(
      private formBuilder: FormBuilder,
      private modalService: BsModalService,
      private offerSrevice: OfferServiceService
  ) { }

  ngOnInit(): void {
    this.offerApplicableOn = ['WASTAGE_CHARGES' , 'MAKING_CHARGES', 'WEIGHT', 'MONEY'];
    this.intializeForm();
    this.setDefaults();
  }

  setDefaults() {
    this.offerForm.get("offerApplicableOn")?.patchValue(null);
  }

  private intializeForm() {
    this.offerForm =  this.formBuilder.group({
      desc : ['', Validators.required],
      offerCode: ['', Validators.required],
      offerApplicableOn : ['', Validators.required],
      expression: ['', Validators.required]
    });
  }

  // convenience getter for easy access to form fields
  get f() { return this.offerForm.controls; }

  onFormSubmit(){
    this.loading = true;
    console.log('Offer type selected ->' + this.offerForm.value.offerApplicableOn);
    let offer = {} as Offer;
    offer.applicableOn = this.offerForm.value.offerApplicableOn;
    offer.desc = this.offerForm.value.desc;
    offer.expression = this.offerForm.value.expression;
    offer.offerCode = this.offerForm.value.offerCode;

    this.offerSrevice.saveOffer(offer).subscribe(response => {
      this.loading = false;
      if(response.statusCode == 201) {
        this.showmodel('Offer Saved Successfully');
      } else {
        this.showmodel(response.message);
      }
    }, err => {
      this.loading = false;
      console.log(err);
      this.showmodel('Failed to login due to backend connection issue');
    }, () => {
      this.offerForm.reset();
    });

  }


  showmodel(message: string) {
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

}
