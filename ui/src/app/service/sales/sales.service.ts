import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Sales } from 'src/app/models/sales/Sales.model';
import { Response } from 'src/app/models/Response.model';

@Injectable({
  providedIn: 'root'
})
export class SalesService {

  constructor(
    private http: HttpClient
  ) { }

  createSales(sales: Sales) {
    return this.http.post<Response<Sales>>(`${environment.apiURL}/sales`, sales);
  }

}
