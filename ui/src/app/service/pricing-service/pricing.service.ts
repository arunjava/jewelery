import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Pricing } from '../../models/pricing/Pricing.model';
import { Response } from 'src/app/models/Response.model';

@Injectable({
  providedIn: 'root'
})
export class PricingService {

  constructor(private http: HttpClient) { }

  getPricingBsdOnProdUOMID(prodID: number, uomID: number) {
    return this.http.get<Response<Pricing>>(`${environment.apiURL}/pricing/` + prodID +  `/` + uomID);
  }

}
