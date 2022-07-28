import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Offer } from 'src/app/models/offers/Offer.model';
import { environment } from 'src/environments/environment';
import { Response } from 'src/app/models/Response.model';

@Injectable({
  providedIn: 'root'
})
export class OfferServiceService {

  constructor(private http: HttpClient) { }

  saveOffer(offer: Offer) {
    return this.http.post<Response<Offer>>(`${environment.apiURL}/offer`, offer);
  }

  getAllOffer() {
    return this.http.get<Response<Array<Offer>>>(`${environment.apiURL}/offer/all`);
  }

}
