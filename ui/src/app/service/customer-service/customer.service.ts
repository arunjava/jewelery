import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Customer } from 'src/app/models/customer/customer.model';
import { Response } from 'src/app/models/Response.model';
import { Scheme } from '../../models/Scheme.model';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  constructor(private http: HttpClient) { }

  saveCustomer(customer: Customer) {
    return this.http.post<Response<Customer>>(`${environment.apiURL}/customer`, customer);
  }

  getCustomer(customerID: number) {
    return this.http.get<Response<Customer>>(`${environment.apiURL}/customer/` + customerID);
  }

  getAllCustomers() {
    return this.http.get<Response<Array<Customer>>>(`${environment.apiURL}/customer`);
  }

  getSchemeForCustomer(customerID: number)   {
    return this.http.get<Response<Array<Scheme>>>(`${environment.apiURL}/customer/` + customerID + '/scheme');
  }

}
