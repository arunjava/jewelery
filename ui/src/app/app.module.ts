import { CUSTOM_ELEMENTS_SCHEMA, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import { LoginComponent } from './components/views/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { SignupComponent } from './components/views/signup/signup.component';
import { ForgetPasswordComponent } from './components/views/forget-password/forget-password.component';
import { HomeComponent } from './components/views/home/home.component';
import { CustomerAddComponent } from './components/views/customer-add/customer-add.component';
import { CustomerUpdateComponent } from './components/views/customer-update/customer-update.component';
import { NgxLoadingModule } from 'ngx-loading';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BsModalService, ModalModule } from 'ngx-bootstrap/modal';
import { ModalComponent } from './components/helper/modal/modal.component';
import { CustomerViewComponent } from './components/views/customer-view/customer-view.component';
import { ListFilterPipe } from './pipes/ListFilterPipe.pipe';
import { TopNavbarComponent } from './components/helper/top-navbar/top-navbar.component';
import { CustomerDetailsComponent } from './components/views/customer-details/customer-details.component';
import { SchemeAddComponent } from './components/views/scheme-add/scheme-add.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    ForgetPasswordComponent,
    HomeComponent,
    CustomerAddComponent,
    CustomerUpdateComponent,
    ModalComponent,
    CustomerViewComponent,
    ListFilterPipe,
    TopNavbarComponent,
    CustomerDetailsComponent,
    SchemeAddComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    NgbModule,
    ModalModule,
    FormsModule,
    NgxLoadingModule.forRoot({}),
    BrowserAnimationsModule,
    ServiceWorkerModule.register('ngsw-worker.js', {
      enabled: environment.production,
      // Register the ServiceWorker as soon as the app is stable
      // or after 30 seconds (whichever comes first).
      registrationStrategy: 'registerWhenStable:30000'
    }),
  ],
  // schemas: [CUSTOM_ELEMENTS_SCHEMA],
  providers: [BsModalService],
  entryComponents: [ModalComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
