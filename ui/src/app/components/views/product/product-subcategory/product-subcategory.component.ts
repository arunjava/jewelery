import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ProductSubCategorySave } from 'src/app/models/product/ProductSubCategorySave.model';
import { ProductServiceService } from 'src/app/service/product-service/product-service.service';
import { ModalComponent } from '../../../helper/modal/modal.component';
import { ProductCategory } from '../../../../models/product/ProductCategory.model';

@Component({
  selector: 'app-product-subcategory',
  templateUrl: './product-subcategory.component.html',
  styleUrls: ['./product-subcategory.component.css']
})
export class ProductSubcategoryComponent implements OnInit {

  productSaveSubCategory = {} as ProductSubCategorySave;
  productCategories = [] as ProductCategory[];
  selectedProdCategory = {} as ProductCategory;

  constructor(
    private formBuilder: FormBuilder,
    private modalService: BsModalService,
    private productService: ProductServiceService) { }

  ngOnInit(): void {
    this.productService.getProductCategories().subscribe(response => {
      if (response.statusCode == 302) {
        this.productCategories = response.result;
      } else {
        this.showmodel(response.message);
      }
    }, error => {
      this.showmodel('Failed to connect to backend!');
    });
  }

  productSubCatForm = this.formBuilder.group({
    subCategoryName: ['', Validators.required],
    description: ['', Validators.required],
    productCategory : ['', Validators.required]
  });

  // convenience getter for easy access to form fields
  get f() { return this.productSubCatForm.controls; }

  onSubmit() {
    console.log(this.productSubCatForm.value.productCategory);
    this.productSaveSubCategory.subCategoryName = this.productSubCatForm.value.subCategoryName;
    this.productSaveSubCategory.description = this.productSubCatForm.value.description;
    this.productSaveSubCategory.productCategory = this.productSubCatForm.value.productCategory;
    this.productService.saveProductSubCategory(this.productSaveSubCategory).subscribe(response => {
      if (response.statusCode == 201) {
        this.showmodel('Created Succesfully');
        this.resetForm();
      } else {
        console.log(response.message);
        this.showmodel(response.message);
      }
    }, error => {
      console.log(error);
      this.showmodel('Failed to connect to backend');
    });
  }

  showmodel(message: string) {
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

  resetForm() {
    this.productSubCatForm.reset();
  }
}
