import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from 'src/app/components/helper/modal/modal.component';
import { Country } from 'src/app/models/address/Country.model';
import { AddressService } from 'src/app/service/address-service/address-service.service';
import { StateRequestModel } from '../../../../models/address/SateSave.model';

@Component({
  selector: 'app-state-save',
  templateUrl: './state-save.component.html',
  styleUrls: ['./state-save.component.css']
})
export class StateSaveComponent implements OnInit {

  countries = [] as Country[];

  constructor(
    private addressService: AddressService,
    private formBuilder: FormBuilder,
    private modalService: BsModalService
  ) { }

  stateForm = this.formBuilder.group({
    country: ['', Validators.required],
    stateName: ['', Validators.required],
    stateCode: ['', Validators.required]
  });

  // convenience getter for easy access to form fields
  get f() { return this.stateForm.controls; }

  onFormSubmit() {
    let stateReq = {} as StateRequestModel;
    stateReq.stateCode = this.stateForm.value.stateCode;
    stateReq.stateName = this.stateForm.value.stateName;
    stateReq.country = this.stateForm.value.country;
    this.addressService.saveState(stateReq).subscribe(
      response => {
        if(response.statusCode == 201) {
          this.showmodel('Created Successfully!');
        } else {
          this.showmodel(response.message);
        }
      }, error => {
        console.log(error);
        this.showmodel('Failed to create!');
      }
    );
  }

  ngOnInit(): void {
    this.addressService.getListOfCountries().subscribe(
      response => {
        if(response.statusCode == 302) {
          this.countries = response.result;
        } else {
    
        }
      } ,  error => {
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
    this.stateForm.reset();
  }

}
