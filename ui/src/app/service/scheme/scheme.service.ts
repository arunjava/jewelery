import { environment } from 'src/environments/environment';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Scheme } from '../../models/scheme/Scheme.model';
import { Response } from 'src/app/models/Response.model';

@Injectable({
  providedIn: 'root'
})
export class SchemeService {

  constructor(
    private http: HttpClient
  ) { }

  saveScheme(scheme: Scheme) {
    return this.http.post<Response<Scheme>>(`${environment.apiURL}/scheme`, scheme);
  }
}
