import { SignupComponent } from './components/views/signup/signup.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/views/login/login.component';
import { ForgetPasswordComponent } from './components/views/forget-password/forget-password.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/views/home/home.component';
import { CustomerAddComponent } from './components/views/customer-add/customer-add.component';
import { CustomerUpdateComponent } from './components/views/customer-update/customer-update.component';
import { CustomerViewComponent } from './components/views/customer-view/customer-view.component';
import { CustomerDetailsComponent } from './components/views/customer-details/customer-details.component';
import { SchemeAddComponent } from './components/views/scheme-add/scheme-add.component';
import { TxSalesComponent } from './components/views/tx-sales/tx-sales.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'home', component: HomeComponent,
    children: [
      {
        path: 'add-customer',
        component: CustomerAddComponent
      },
      {
        path: 'customer-update/:id',
        component: CustomerUpdateComponent
      },
      {
        path: 'list-customer',
        component: CustomerViewComponent
      },
      {
        path: 'customer-details/:id',
        component: CustomerDetailsComponent
      },
      {
        path: 'customer-scheme/:id',
        component: SchemeAddComponent
      },
      {
        path: 'tx-sales',
        component: TxSalesComponent
      }
    ]
  },
  { path: 'signup', component: SignupComponent },
  { path: 'forget-password', component: ForgetPasswordComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), HttpClientModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
