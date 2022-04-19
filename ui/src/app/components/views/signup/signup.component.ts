import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { UserSignup } from '../../../models/user/UserSignup.model';
import { UserService } from '../../../service/user-service/user-service.service';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  userSignupModel = new UserSignup();

  constructor(private formBuilder: FormBuilder,
              private userService: UserService) { }

  ngOnInit(): void {
  }

  signupForm = this.formBuilder.group({
    username: ['', Validators.required],
    password: ['', Validators.required],
    repassword: ['', Validators.required],
    firstName: ['', Validators.required],
    lastName: ['', Validators.required]
  });


  onFormSubmit(): void {
    this.userSignupModel.firstName = this.signupForm.value.firstName;
    this.userSignupModel.lastName = this.signupForm.value.lastName;
    this.userSignupModel.username = this.signupForm.value.username;
    this.userSignupModel.password = this.signupForm.value.password;

    console.log(JSON.stringify(this.userSignupModel));

    this.userService.signup(this.userSignupModel).subscribe(response => {
      console.log(response);
    });

  }

}
