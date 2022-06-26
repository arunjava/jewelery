import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../../service/customer-service/customer.service';
import { Customer } from '../../../models/customer/customer.model';
import { Address } from '../../../models/Address.model';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  customerId: number  = 0;
  customer!: Customer;
  address!: Address;

  constructor(
    private _route: ActivatedRoute,
    private customerService: CustomerService
  ) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      console.log(params['id']);
      this.customerId = params['id'];
      this.customerService.getCustomer(this.customerId).subscribe(response => {
        if (response.statusCode == 200) {
          this.customer = response.result;
          this.address = this.customer.address;
          console.log(this.address);
          console.log(this.customer);
        } else {
          console.log('Customer not found');
        }
      })
    })
  }

}
