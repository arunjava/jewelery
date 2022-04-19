import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment.prod';
import { UserSignup } from '../../models/user/UserSignup.model';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  login(username: string, password: string) {
    return this.http.post<any>(`${environment.apiURL}/user/login`, { username, password });
  }

  signup(userSignup: UserSignup) {
    console.log(environment.apiURL + '/user/signup');
    return this.http.post<any>(environment.apiURL + '/user/signup', userSignup);
  }

  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

}
