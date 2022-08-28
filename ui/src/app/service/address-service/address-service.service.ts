import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Country } from '../../models/address/Country.model';
import { Response } from 'src/app/models/Response.model';
import { State } from '../../models/address/State.model';
import { District } from '../../models/address/District.model';
import { SubDistrict } from '../../models/address/SubDistrict.model';
import { StateRequestModel } from 'src/app/models/address/SateSave.model';

@Injectable({
  providedIn: 'root'
})
export class AddressService {

  constructor(private http: HttpClient) { }

  getListOfCountries() {
    return this.http.get<Response<Country[]>>(`${environment.apiURL}/address/countries`);
  }

  getListOfStatesBsdOnCountryID(countryId: number) {
    return this.http.get<Response<State[]>>(`${environment.apiURL}/address/states/` + countryId);
  }

  getListOfDistrictBsdOnStateID(stateId: number) {
    return this.http.get<Response<District[]>>(`${environment.apiURL}/address/district/` + stateId);
  }

  getListOfSubDistrictBsdOnDistrictID(districtID: number) {
    return this.http.get<Response<SubDistrict[]>>(`${environment.apiURL}/address/subDistrict/` + districtID);
  }

  saveState(state: StateRequestModel) {
    return this.http.post<Response<State>>(`${environment.apiURL}/address/save/state`,  state);
  }

  saveDistrict(district: StateRequestModel) {
    return this.http.post<Response<State>>(`${environment.apiURL}/address/save/district`,  district);
  }

  saveSubDistrict(state: StateRequestModel) {
    return this.http.post<Response<State>>(`${environment.apiURL}/address/save/subDistrict`,  state);
  }

}
