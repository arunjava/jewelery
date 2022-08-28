import { ProductCategory } from './ProductCategory.model';

export interface ProductSubCategorySave {

  subCategoryName: string;
  description: string;
  isActive: boolean;
  productCategory: ProductCategory;
}
