import { Component, OnInit, Inject } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { User } from '../../../models/user/UserSignup.model';
import { UserService } from '../../../service/user-service/user-service.service';
import { ModalComponent } from '../../helper/modal/modal.component';
import { BsModalService } from 'ngx-bootstrap/modal';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {
  public loading = false;
  userSignupModel = new User();

  constructor(private formBuilder: FormBuilder,
    private userService: UserService,
    @Inject(BsModalService)
    private modalService: BsModalService) { }

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
    this.loading = true;

    this.userSignupModel.firstName = this.signupForm.value.firstName;
    this.userSignupModel.lastName = this.signupForm.value.lastName;
    this.userSignupModel.username = this.signupForm.value.username;
    this.userSignupModel.password = this.signupForm.value.password;

    console.log(JSON.stringify(this.userSignupModel));

    this.userService.signup(this.userSignupModel).subscribe(response => {
      this.loading = false;
      if (response.statusCode == 201) {
        this.showmodel(response.message);
        this.signupForm.reset();
      } else {
        this.showmodel(JSON.stringify(response.result));
      }
    }, err => {
      console.log(err);
      this.loading = false;
      this.showmodel(err.message);
    });

  }

  showmodel(message: string) {
    console.log('Inside modal dispaly');
    console.log(message);
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }
}
