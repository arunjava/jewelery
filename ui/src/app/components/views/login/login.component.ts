import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { BsModalService, ModalOptions, BsModalRef } from 'ngx-bootstrap/modal';
import { LoginService } from '../../../service/login/login.service';
import { UserService } from '../../../service/user-service/user-service.service';
import { ModalComponent } from '../../helper/modal/modal.component';
import { ModalBox } from '../../../models/ModalBox.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public loading = false;

  loginform = this.formBuilder.group({
    username: ['', Validators.required,Validators.email],
    password: ['', Validators.required]
  });

  public modalRef = {} as BsModalRef;

  constructor(
    private formBuilder: FormBuilder,
    private userService: UserService,
    private router: Router,
    @Inject(BsModalService)
    private modalService: BsModalService
  ) { }


  ngOnInit(): void {
  }

  // convenience getter for easy access to form fields
  get f() { return this.loginform.controls; }

  onSubmit() {
    this.loading = true;
    console.log('Login button pressed');
    console.log(this.loginform.value.username + '' + this.loginform.value.password);
    // this.userService.jwtAuthentication(this.loginform.value.username, this.loginform.value.password).subscribe(response => {
    this.userService.login(this.loginform.value.username, this.loginform.value.password).subscribe(response => {
      this.loading = false;
      if (response.statusCode == 200) {
        localStorage.setItem('user_dtls', JSON.stringify(response.result));
        this.router.navigate(['home']);
      } else {
        this.openModal(response.message);
      }
    }, err => {
      this.loading = false;
      console.log(err);
      this.openModal('Failed to login due to backend connection issue');
    }
    );
  }

  openModal(body: string) {
    this.modalRef = this.modalService.show(ModalComponent);
    this.modalRef.content.title = 'Login';
    this.modalRef.content.message = body;

  }

}
