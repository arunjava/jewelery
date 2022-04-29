import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { LoginService } from '../../../service/login/login.service';
import { UserService } from '../../../service/user-service/user-service.service';


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
    private userService: UserService,
    private router: Router
  ) { }


  ngOnInit(): void {
  }

  onSubmit() {
    console.log('Login button pressed');
    console.log(this.loginform.value.username + '' + this.loginform.value.password);
    this.userService.login(this.loginform.value.username, this.loginform.value.password).subscribe(response => {
      console.log("Server response -->" + response.statusCode);
      if (response.statusCode === 200) {
        sessionStorage.setItem('user', JSON.stringify(response.result));
        // sessionStorage.setItem('user', response.result);
        this.router.navigate(['home']);
      }
    }, err => {
      console.log(err);
    }
    );
  }

}
