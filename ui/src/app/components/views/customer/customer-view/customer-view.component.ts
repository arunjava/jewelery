import { CustomerService } from '../../../../service/customer-service/customer.service';
import { Component, OnInit, PipeTransform } from '@angular/core';
import { Customer } from 'src/app/models/customer/customer.model';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { ProductExchange } from '../../../../models/product/ProductExchange.model';

@Component({
  selector: 'app-customer-view',
  templateUrl: './customer-view.component.html',
  styleUrls: ['./customer-view.component.css']
})
export class CustomerViewComponent implements OnInit {

  page = 1;
  pageSize = 5;
  collectionSize: number = 0;

  customers = [] as Customer[];
  productExhchangeMap = new Map<number, ProductExchange[]>();
  filteredCustomers = [] as Customer[];
  filter = new FormControl('');

  constructor(
    private customerService: CustomerService,
    private router: Router
  ) {
  }

  ngOnInit(): void {

    this.customerService.getAllCustomers().subscribe(response => {
      if (response.statusCode == 302) {
        this.customers = response.result;
        this.filteredCustomers = this.customers;
        this.collectionSize = this.customers.length;

        this.customers.forEach(customer => {
          this.customerService.getProductExcahngeSumBsdOnCustID(customer.custId).subscribe(resp => {
            if (resp.statusCode == 302) {
              this.productExhchangeMap.set(customer.custId, resp.result);
            }
          });
        });
      }
    }, error => {
      console.log(error);
    });
  }

  search(searchString: string) {
    this.filteredCustomers = this.customers.filter(customer => {
      return customer.customerName.toLowerCase().includes(searchString.toLowerCase()) ||
        customer.primaryContactNo.toLowerCase().includes(searchString.toLowerCase()) ||
        customer.referralCode.toLowerCase().includes(searchString.toLowerCase());
    });
    this.collectionSize = this.filteredCustomers.length;
  }

  editCustomer(customerID: number) {
    console.log(customerID);
    this.router.navigateByUrl('/home/customer-update/' + customerID);
  }

}
