import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../../../service/login/login.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginform = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required]
  });

  constructor(
    private formBuilder: FormBuilder,
    private loginService: LoginService
  ) { }


  ngOnInit(): void {
  }

  onSubmit() {
    console.log('Login button pressed');
    console.log(this.loginform.value.username);
    this.loginService.login(this.loginform.value.username, this.loginform.value.password).pipe(

    );
  }

}
