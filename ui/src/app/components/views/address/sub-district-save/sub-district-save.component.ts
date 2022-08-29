import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { Country } from 'src/app/models/address/Country.model';
import { District } from 'src/app/models/address/District.model';
import { DistrictSaveRequest } from 'src/app/models/address/DistrictSaveRequest.model';
import { SubDistrictSaveRequest } from 'src/app/models/address/SubDistrictSaveRequest.model';
import { AddressService } from 'src/app/service/address-service/address-service.service';
import { State } from '../../../../models/address/State.model';
import { ModalComponent } from '../../../helper/modal/modal.component';

@Component({
  selector: 'app-sub-district-save',
  templateUrl: './sub-district-save.component.html',
  styleUrls: ['./sub-district-save.component.css']
})
export class SubDistrictSaveComponent implements OnInit {
  countries = [] as Country[];
  states = [] as State[];
  districts = [] as District[];
  selectedCountry = {} as Country;
  selectedSate = {} as State;
  selectedDistrict = {} as District;

  constructor(
    private addressService: AddressService,
    private formBuilder: FormBuilder,
    private modalService: BsModalService
  ) { }

  ngOnInit(): void {
    this.addressService.getListOfCountries().subscribe(
      response => {
        if (response.statusCode == 302) {
          this.countries = response.result;
        }
      }, error => {
        console.log(error);
      }
    );
  }

  onCountrySelection(country: Country) {
    this.addressService.getListOfStatesBsdOnCountryID(country.id).subscribe(
      response => {
        if (response.statusCode == 302) {
          this.states = response.result;
        }
      }
    );
  }

  onStateSelection(state: State) {
    this.addressService.getListOfDistrictBsdOnStateID(state.id).subscribe(
      response => {
        if(response.statusCode == 302) {
          this.districts = response.result;
        } else {

        }
      } , error => {
        console.log(error);
      }
    );
  }

  subDistrictForm = this.formBuilder.group({
    country: ['', Validators.required],
    state: ['', Validators.required],
    district: ['', Validators.required],
    subDistrictName : ['', Validators.required]
  });

  onFormSubmit() {
    let subDistrictReq = {} as SubDistrictSaveRequest;
    subDistrictReq.subDistrictName = this.subDistrictForm.value.subDistrictName;
    subDistrictReq.district = this.subDistrictForm.value.district;

    this.addressService.saveSubDistrict(subDistrictReq).subscribe(
      response => {
        if(response.statusCode == 201) {
          this.showmodel('SubDistrict Created Successfully!');
          this.resetForm();
        } else {
          this.showmodel(response.message);
        }
      }, error => {
        console.log(error);
      }
    );

  }

  showmodel(message: string) {
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

  resetForm() {
    this.subDistrictForm.reset();
  }

}
