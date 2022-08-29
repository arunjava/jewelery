import { District } from './District.model';

export interface SubDistrictSaveRequest {
  subDistrictName: string;
  subDistrictCode: string;
  district: District;
}