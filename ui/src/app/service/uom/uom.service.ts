import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UOM } from 'src/app/models/uom/UOM.model';
import { Response } from 'src/app/models/Response.model';

@Injectable({
  providedIn: 'root'
})
export class UomService {

  constructor(private http: HttpClient) { }

  getAllActiveUOMSBsdOnCategoryID(catID: number) {
    return this.http.get<Response<Array<UOM>>>(`${environment.apiURL}/uom/category/` + catID);
  }

}
