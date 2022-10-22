import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ProductCategory } from 'src/app/models/product/ProductCategory.model';
import { Response } from 'src/app/models/Response.model';
import { environment } from 'src/environments/environment';
import { ProductSubCategory } from '../../models/product/ProductSubCategory.model';
import { Product } from '../../models/product/Product.model';
import { UOM } from '../../models/uom/UOM.model';
import { ProductCategorySave } from 'src/app/models/product/ProductCategorySave.model';
import { ProductSubCategorySave } from 'src/app/models/product/ProductSubCategorySave.model';
import { ProductSave } from '../../models/product/ProductSave.model';
import { Pricing } from 'src/app/models/pricing/Pricing.model';

@Injectable({
  providedIn: 'root'
})
export class ProductServiceService {

  constructor(
    private http: HttpClient) { }

  getProductCategories() {
    return this.http.get<Response<ProductCategory[]>>(`${environment.apiURL}/product/category`);
  }

  getProductSubCategories(catId: number) {
    return this.http.get<Response<ProductSubCategory[]>>(`${environment.apiURL}/product/sub_category/category/` + catId);
  }

  getProducts(subCatId: number) {
    return this.http.get<Response<Product[]>>(`${environment.apiURL}/product/sub_category/products/` + subCatId);
  }

  /**
   * UOM related backend calls
   */

  getUOMByID(uomID: number) {
    return this.http.get<Response<UOM>>(`${environment.apiURL}/uom/` + uomID);
  }

  saveProduct(product: ProductSave) {
    return this.http.post<Response<Product>>(`${environment.apiURL}/product`, product);
  }

  saveProductCategory(prodCat: ProductCategorySave) {
    return this.http.post<Response<ProductCategory>>(`${environment.apiURL}/product/category`, prodCat);
  }

  saveProductSubCategory(prodSubCat: ProductSubCategorySave) {
    return this.http.post<Response<ProductSubCategory>>(`${environment.apiURL}/product/sub_category`, prodSubCat);
  }

  saveProductPricing(pricing: Pricing) {
    return this.http.post<Response<Pricing>>(`${environment.apiURL}/pricing`, pricing);
  }


}
