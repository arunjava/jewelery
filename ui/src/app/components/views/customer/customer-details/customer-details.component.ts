import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CustomerService } from '../../../../service/customer-service/customer.service';
import { Customer } from '../../../../models/customer/customer.model';
import { Address } from '../../../../models/Address.model';
import { Sales } from 'src/app/models/sales/Sales.model';
import { SalesService } from 'src/app/service/sales/sales.service';

@Component({
  selector: 'app-customer-details',
  templateUrl: './customer-details.component.html',
  styleUrls: ['./customer-details.component.css']
})
export class CustomerDetailsComponent implements OnInit {

  customerId: number  = 0;
  customer!: Customer;
  address!: Address;
  sales = [] as Sales[];

  constructor(
    private _route: ActivatedRoute,
    private customerService: CustomerService,
    private salesService: SalesService
  ) { }

  ngOnInit(): void {
    this._route.params.subscribe(params => {
      console.log(params['id']);
      this.customerId = params['id'];
      this.customerService.getCustomer(this.customerId).subscribe(response => {
        if (response.statusCode == 200) {
          this.customer = response.result;
          this.address = this.customer.address;
          this.salesService.getAllSalesBsdOnCustomerID(this.customerId).subscribe(response => {
            if(response.statusCode == 302) {
               this.sales = response.result;
            } else {
              console.log('No data available');
            }
          }, error => {
            console.log('Failed to retreive sales');
          });
        } else {
          console.log('Customer not found');
        }
      })
    })
  }
}
