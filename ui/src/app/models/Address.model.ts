import { Country } from './address/Country.model';
import { SubDistrict } from './address/SubDistrict.model';
import { District } from './address/District.model';
import { State } from './address/State.model';

export interface Address {

  addr1: string;
  addr2: string;
  addr3: string;
  subDistrict: SubDistrict;
  district: District;
  state: State;
  country: Country;
  pincode: string;
  landMark: string;
  locality: string;

}