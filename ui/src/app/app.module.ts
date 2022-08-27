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
import { CustomerUpdateComponent } from './components/views/customer/customer-update/customer-update.component';
import { NgxLoadingModule } from 'ngx-loading';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { BsModalService, ModalModule } from 'ngx-bootstrap/modal';
import { ModalComponent } from './components/helper/modal/modal.component';
import { CustomerViewComponent } from './components/views/customer/customer-view/customer-view.component';
import { ListFilterPipe } from './pipes/ListFilterPipe.pipe';
import { TopNavbarComponent } from './components/helper/top-navbar/top-navbar.component';
import { CustomerDetailsComponent } from './components/views/customer/customer-details/customer-details.component';
import { SchemeAddComponent } from './components/views/scheme/scheme-add/scheme-add.component';
import { TxSalesComponent } from './components/views/tx-sales/tx-sales.component';
import { OffersAddComponent } from './components/views/offers/offers-add/offers-add.component';
import { OffersViewComponent } from './components/views/offers/offers-view/offers-view.component';
import { SchemeViewComponent } from './components/views/scheme/scheme-view/scheme-view.component';
import { CustomerSchemeComponent } from './components/views/customer/customer-scheme/customer-scheme.component';
import { CustomerAddComponent } from './components/views/customer/customer-add/customer-add.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { JwtInterceptor } from './helper/jwt.interceptor';
import { ErrorInterceptor } from './helper/error.interceptor';

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
    SchemeAddComponent,
    TxSalesComponent,
    OffersAddComponent,
    OffersViewComponent,
    SchemeViewComponent,
    CustomerSchemeComponent
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
  providers:
  [
    BsModalService,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
  ],
  entryComponents: [ModalComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
