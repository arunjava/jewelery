import { Address } from '../Address.model';
export interface Customer {

  custId: number;
  customerName: string;
  primaryContactNo: string;
  alterNateContactNo: string;
  referralCode: string;
  address: Address;

}
