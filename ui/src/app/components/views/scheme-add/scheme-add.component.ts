import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../../service/customer-service/customer.service';
import { FormBuilder, Validators } from '@angular/forms';
import { NgbDateStruct, NgbDateAdapter, NgbCalendar } from '@ng-bootstrap/ng-bootstrap';
import { Scheme } from '../../../models/scheme/Scheme.model';
import { SchemeService } from 'src/app/service/scheme/scheme.service';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from '../../helper/modal/modal.component';

@Component({
  selector: 'app-scheme-add',
  templateUrl: './scheme-add.component.html',
  styleUrls: ['./scheme-add.component.css']
})
export class SchemeAddComponent implements OnInit {
  
  scheme = {} as Scheme;
  customerID: number = 0;
  loading: boolean = false;
  currentDate:Date = new Date();
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
    private dateAdapter: NgbDateAdapter<string>
  ) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.customerID = params['id'];
      console.log(this.customerID);
    })
  }

  schemeForm = this.formBuilder.group({
    schemeName: ['', Validators.required],
    description: ['', Validators.required],
    startDate: ['', Validators.required],
    endDate: ['', Validators.required]
  });

  onFormSubmit() {
    console.log('Submit button clicked --> ' + JSON.stringify(this.schemeForm.value));
    this.loading = true;
    this.scheme.scheme_active = true;
    this.scheme.scheme_desc = this.schemeForm.value.description;
    this.scheme.scheme_name = this.schemeForm.value.schemeName;
    this.scheme.beginDate = new Date(this.schemeForm.value.startDate.year, this.schemeForm.value.startDate.month, this.schemeForm.value.startDate.date);
    this.schmeService.saveScheme(this.scheme).subscribe(
      (response) => {
        this.loading = false;
        if(response.statusCode == 201) {
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

  showmodel(message: string, title : string) {
    console.log('Inside modal dispaly');
    console.log(message);
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = title;
    modalRef.content.message = message;
  }

}
