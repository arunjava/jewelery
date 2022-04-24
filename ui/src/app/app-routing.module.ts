import { SignupComponent } from './components/views/signup/signup.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/views/login/login.component';
import { ForgetPasswordComponent } from './components/views/forget-password/forget-password.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/views/home/home.component';
import { CustomerAddComponent } from './components/views/customer-add/customer-add.component';
import { CustomerUpdateComponent } from './components/views/customer-update/customer-update.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'home', component: HomeComponent,
    children:[
      {
        path:'add-customer',
        component: CustomerAddComponent
      },
      {
        path:'update-customer',
        component: CustomerUpdateComponent
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
