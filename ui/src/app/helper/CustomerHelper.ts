import { Injectable } from "@angular/core";
import { Customer } from "../models/customer/customer.model";
import { FormGroup } from '@angular/forms';
import { Address } from "../models/Address.model";

@Injectable({
  providedIn: 'root'
})
export class CustomerHelper {

    getCustomerObjBsdOnFormVal(customerForm : FormGroup)  : Customer {
      let customer = {} as Customer;
      let address = {} as Address;
      customer.customerName = customerForm.value.customerName;
      customer.primaryContactNo = customerForm.value.phoneNumber;
      customer.alterNateContactNo = customerForm.value.altPhoneNumber;
      customer.referralCode = customerForm.value.referralCode;
      address.addr1 = customerForm.value.inputAddress;
      address.addr2 = customerForm.value.inputAddress2;
      if(customerForm.value.subDistirctFormName) {
        address.subDistrict = customerForm.value.subDistirctFormName.id !== undefined ? customerForm.value.subDistirctFormName : null;
      }
      address.district = customerForm.value.districtFormName;
      address.state = customerForm.value.stateFormName;
      address.country = customerForm.value.countryFormName;
      address.pincode = customerForm.value.pincode;
      address.locality = customerForm.value.locality;
      customer.address = address;
      return customer;
    }

}
