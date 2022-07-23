import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddressService } from '../../../service/address-service/address-service.service';
import { Country } from '../../../models/address/Country.model';
import { State } from '../../../models/address/State.model';
import { District } from '../../../models/address/District.model';
import { SubDistrict } from '../../../models/address/SubDistrict.model';
import { Customer } from 'src/app/models/customer/customer.model';
import { CustomerService } from '../../../service/customer-service/customer.service';
import { Address } from 'src/app/models/Address.model';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from '../../helper/modal/modal.component';
import { CustomerHelper } from '../../../helper/CustomerHelper';

@Component({
  selector: 'app-customer-add',
  templateUrl: './customer-add.component.html',
  styleUrls: ['./customer-add.component.css']
})
export class CustomerAddComponent implements OnInit {

  public loading = false;
  public customer = {} as Customer;
  public address = {} as Address;
  public countries = [] as Array<Country>;
  public states = [] as State[];
  public districts = [] as District[];
  public subDistricts = [] as SubDistrict[];

  public selectedCountry = {} as Country;
  public selectedState = {} as State;
  public selectedDistrict = {} as District;
  public selectedSubDistrict = {} as SubDistrict;

  constructor(private formBuilder: FormBuilder,
    private addressService: AddressService,
    private customerService: CustomerService,
    @Inject(BsModalService)
    private modalService: BsModalService,
    private customerHelper: CustomerHelper) { }

  ngOnInit(): void {
    this.addressService.getListOfCountries().subscribe(response => {
      this.countries = response.result;
    });
  }

  customerForm = this.formBuilder.group({
    customerName: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    inputAddress: ['', Validators.required],
    inputAddress2: ['', Validators.required],
    countryFormName: ['', Validators.required],
    stateFormName: ['', Validators.required],
    districtFormName: ['', Validators.required],
    subDistirctFormName: ['', Validators.required],
    pincode: ['', Validators.required],
    locality: ['', Validators.required],
    referralCode: [],
    altPhoneNumber: []
  });

  onCountrySelection(country: Country) {
    console.log('Selected count -->' + country.countryName + ' ' + country.countryCode);
    this.addressService.getListOfStatesBsdOnCountryID(country.id).subscribe(response => {
      this.states = response.result;
    });
  }

  onStateSelection(state: State) {
    this.addressService.getListOfDistrictBsdOnStateID(state.id).subscribe(response => {
      this.districts = response.result;
    });
  }

  onDistrictSelection(district: District) {
    this.addressService.getListOfSubDistrictBsdOnDistrictID(district.id).subscribe(response => {
      this.subDistricts = response.result;
    });
  }

  onFormSubmit() {
    this.loading = true;
    console.log(JSON.stringify(this.customerHelper.getCustomerObjBsdOnFormVal(this.customerForm)));
    this.customerService.saveCustomer(this.customerHelper.getCustomerObjBsdOnFormVal(this.customerForm)).subscribe(response => {
      this.loading = false;
      if (response.statusCode == 201) {
        this.showmodel('Saved Successfully');
        this.customerForm.reset();
      } else {
        this.showmodel(response.message)
      }
    },
      err => {
        console.log(err);
        this.loading = false;
        this.showmodel(err.message);
      });
  }

  getCustomerObj(): Customer {
    console.log(this.customerForm);
    // this.customer.customerName = this.customerForm.value.customerName;
    // this.customer.primaryContactNo = this.customerForm.value.phoneNumber;
    // this.customer.alterNateContactNo = this.customerForm.value.altPhoneNumber;
    // this.customer.referralCode = this.customerForm.value.referralCode;
    // this.address.addr1 = this.customerForm.value.inputAddress;
    // this.address.addr2 = this.customerForm.value.inputAddress2;
    // this.address.subDistrict = this.customerForm.value.subDistirctFormName;
    // this.address.district = this.customerForm.value.districtFormName;
    // this.address.state = this.customerForm.value.stateFormName;
    // this.address.country = this.customerForm.value.countryFormName;
    // this.address.pincode = this.customerForm.value.pincode;
    // this.address.locality = this.customerForm.value.locality;
    // this.customer.address = this.address;
     return this.customer;
  }

  showmodel(message: string) {
    console.log('Inside modal dispaly');
    console.log(message);
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

}
