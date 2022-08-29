import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { Country } from 'src/app/models/address/Country.model';
import { DistrictSaveRequest } from 'src/app/models/address/DistrictSaveRequest.model';
import { AddressService } from 'src/app/service/address-service/address-service.service';
import { State } from '../../../../models/address/State.model';
import { ModalComponent } from '../../../helper/modal/modal.component';

@Component({
  selector: 'app-district-save',
  templateUrl: './district-save.component.html',
  styleUrls: ['./district-save.component.css']
})
export class DistrictSaveComponent implements OnInit {

  countries = [] as Country[];
  states = [] as State[];
  selectedCountry =  {} as Country;
  selectedSate = {} as State;

  constructor(
    private addressService: AddressService,
    private formBuilder: FormBuilder,
    private modalService: BsModalService
  ) { }

  ngOnInit(): void {
    this.addressService.getListOfCountries().subscribe(
      response => {
        if(response.statusCode == 302) {
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
        if(response.statusCode == 302) {
          this.states = response.result;
        }
      }
    );
  }

  districtForm = this.formBuilder.group({
    country: ['', Validators.required],
    state: ['', Validators.required],
    districtName: ['', Validators.required]
  });

  onFormSubmit() {
    let districtReq = {} as DistrictSaveRequest;
    districtReq.districtName = this.districtForm.value.districtName;
    districtReq.state = this.districtForm.value.state;

    this.addressService.saveDistrict(districtReq).subscribe(
      response => {
        if(response.statusCode == 201) {
          this.showmodel('Created Successfully!');
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
    this.districtForm.reset();
  }

}
