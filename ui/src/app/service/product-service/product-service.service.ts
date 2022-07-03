import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ProductCategory } from 'src/app/models/product/ProductCategory.model';
import { Response } from 'src/app/models/Response.model';
import { environment } from 'src/environments/environment';
import { ProductSubCategory } from '../../models/product/ProductSubCategory.model';
import { Product } from '../../models/product/Product.model';
import { UOM } from '../../models/uom/UOM.model';

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

}
