import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Customer } from 'src/app/models/customer/customer.model';
import { CustomerService } from 'src/app/service/customer-service/customer.service';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from 'src/app/components/helper/modal/modal.component';
import { UomService } from 'src/app/service/uom/uom.service';
import { SchemeService } from '../../../../service/scheme/scheme.service';
import { Scheme } from 'src/app/models/scheme/Scheme.model';
import { UOM } from 'src/app/models/uom/UOM.model';
import { CustomerScheme } from 'src/app/models/customer/CustomerScheme.model';

@Component({
  selector: 'app-customer-scheme',
  templateUrl: './customer-scheme.component.html',
  styleUrls: ['./customer-scheme.component.css']
})
export class CustomerSchemeComponent implements OnInit {

  customer = {} as Customer;
  schemes!: Scheme[];
  uoms!: UOM[];
  selectedUOM!: UOM;
  selectedScheme = {} as Scheme;
  customerSchemeForm = this.formBuilder.group({
    customerName: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    uom: ['', Validators.required],
    scheme: ['', Validators.required],
    uomExchangeVal: ['', Validators.required]
  });

  get f() {
    return this.customerSchemeForm.controls;
  }

  constructor(
    private formBuilder: FormBuilder,
    private modalService: BsModalService,
    private customerService: CustomerService,
    private uomService: UomService,
    private schemeService: SchemeService
  ) { }

  ngOnInit(): void {
    this.schemeService.getAllActiveSchemes().subscribe(response => {
      if (response.statusCode == 302) {
        this.schemes = response.result;
      } else {
        console.log('Failed to get schemes->' + response.message);
      }
    }, error => {
      console.log(error.statusText);
      // this.showmodel(error.statusText);
    });

    this.uomService.getAllActiveUOMSBsdOnCategoryID(1).subscribe(response => {
      if (response.statusCode == 302) {
        this.uoms = response.result;
      } else {
        console.log('Failed to get schemes->' + response.message);
      }
    }, error => {
      console.log(error.statusText);
    })

  }

  onFormSubmit() {
    let customerScheme = {} as CustomerScheme;
    customerScheme.customerID = this.customer.custId;
    customerScheme.schemeID = this.selectedScheme.scheme_id;
    customerScheme.uomExchangeID = this.selectedUOM.uom_id;
    customerScheme.uomExchangeVal = this.customerSchemeForm.value.uomExchangeVal;

    this.customerService.saveCustomerScheme(customerScheme).subscribe(response => {
      if (response.statusCode == 201) {
        this.showmodel('Scheme Created Successfully!');
      } else {
        this.showmodel(response.message);
      }
    }, error => {
      this.showmodel(error.statusText);
    });

  }

  getCustomerBsdOnPhoneNumber() {
    let phoneNum = this.customerSchemeForm.value.phoneNumber;
    if (phoneNum) {
      this.customerService.getCustomerBsdOnContactNumber(phoneNum).subscribe(response => {
        if (response.statusCode == 302) {
          this.customer = response.result;
          console.log(JSON.stringify(this.customer));
          this.customerSchemeForm.controls['customerName'].setValue(this.customer.customerName);
        } else {
          this.resetValues();
          this.showmodel('Phone number not found!');
        }
      });
    } else {
      this.resetValues();
      this.showmodel('Invalid Phone number!')
    }
  }

  resetValues() {
    this.customer = {} as Customer;
    this.customerSchemeForm.reset();
  }

  showmodel(message: string) {
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

}
