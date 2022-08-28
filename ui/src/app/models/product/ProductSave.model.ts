import { ProductSubCategory } from './ProductSubCategory.model';
export interface ProductSave {
  productName: string;
  description: string;
  uomID: number;
  productSubCategory: ProductSubCategory;  
}
