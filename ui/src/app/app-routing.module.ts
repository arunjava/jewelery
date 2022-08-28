import { SignupComponent } from './components/views/signup/signup.component';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/views/login/login.component';
import { ForgetPasswordComponent } from './components/views/forget-password/forget-password.component';
import { HttpClient, HttpClientModule } from '@angular/common/http';
import { HomeComponent } from './components/views/home/home.component';
import { CustomerAddComponent } from './components/views/customer/customer-add/customer-add.component';
import { CustomerUpdateComponent } from './components/views/customer/customer-update/customer-update.component';
import { CustomerViewComponent } from './components/views/customer/customer-view/customer-view.component';
import { CustomerDetailsComponent } from './components/views/customer/customer-details/customer-details.component';
import { SchemeAddComponent } from './components/views/scheme/scheme-add/scheme-add.component';
import { TxSalesComponent } from './components/views/tx-sales/tx-sales.component';
import { OffersAddComponent } from './components/views/offers/offers-add/offers-add.component';
import { OffersViewComponent } from './components/views/offers/offers-view/offers-view.component';
import { SchemeViewComponent } from './components/views/scheme/scheme-view/scheme-view.component';
import { CustomerSchemeComponent } from './components/views/customer/customer-scheme/customer-scheme.component';
import { AuthGuardService } from './security/auth.guard';
import { ProductCategoryComponent } from './components/views/product/product-category/product-category.component';
import { ProductSubcategoryComponent } from './components/views/product/product-subcategory/product-subcategory.component';
import { ProductComponent } from './components/views/product/product/product.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  {
    path: 'home', component: HomeComponent,
    canActivate: [AuthGuardService],
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
        path: 'customer-scheme/add',
        component: CustomerSchemeComponent
      },
      {
        path: 'tx-sales',
        component: TxSalesComponent
      },
      {
        path: 'scheme-add',
        component: SchemeAddComponent
      },
      {
        path: 'scheme-view',
        component: SchemeViewComponent
      },
      {
        path: 'offers-add',
        component: OffersAddComponent
      },
      {
        path: 'offers-view',
        component: OffersViewComponent
      },
      {
        path: 'product-cat',
        component: ProductCategoryComponent
      },
      {
        path: 'product-subcat',
        component: ProductSubcategoryComponent
      },
      {
        path: 'product-save',
        component: ProductComponent
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
