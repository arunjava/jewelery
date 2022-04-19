import { environment } from './../../../environments/environment.prod';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    let headers = new HttpHeaders();
    headers = headers.set('content-type', 'application/json');
    headers = headers.set('username', username);
    headers = headers.set('password', password);

    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'username': username,
        'password': password
      })
    };

    return this.http.post<any>(`${environment.apiURL}/user/login`, { username, password });
  }

}
