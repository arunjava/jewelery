import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../../models/user/UserSignup.model';
import { environment } from 'src/environments/environment';
import { Response } from 'src/app/models/Response.model';
import { UserAuthResponse } from 'src/app/models/user/UserAuthResponse.model';
import { BehaviorSubject, Observable } from 'rxjs';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(
    private http: HttpClient,
    private router: Router
    ) { }

  login(username: string, password: string) {
    return this.http.post<Response<UserAuthResponse>>(`${environment.apiURL}/user/login`, { username, password });
  }

  signup(userSignup: User) {
    console.log(environment.apiURL + '/user/signup');
    return this.http.post<any>(environment.apiURL + '/user/signup', userSignup);
  }

  createBasicAuthToken(username: String, password: String) {
    return 'Basic ' + window.btoa(username + ":" + password)
  }

  jwtAuthentication(username: string, password: string) {
    return this.http.post<any>(`${environment.apiURL}/authenticate`, { username, password });
  }

  logout() {
    localStorage.clear();
    this.router.navigate(['/login']);
  }

}
