import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../../service/customer-service/customer.service';

@Component({
  selector: 'app-scheme-add',
  templateUrl: './scheme-add.component.html',
  styleUrls: ['./scheme-add.component.css']
})
export class SchemeAddComponent implements OnInit {

  customerID: number = 0;

  constructor(
    private _route: ActivatedRoute,
    private customerServie: CustomerService
  ) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      this.customerID = params['id'];
      console.log(this.customerID);
    })
  }

}
