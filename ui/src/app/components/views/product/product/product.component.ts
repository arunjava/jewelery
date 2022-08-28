import { ProductSubCategory } from './../../../../models/product/ProductSubCategory.model';
import { Component, OnInit } from '@angular/core';
import { ProductServiceService } from 'src/app/service/product-service/product-service.service';
import { ProductCategory } from '../../../../models/product/ProductCategory.model';
import { ModalComponent } from '../../../helper/modal/modal.component';
import { FormBuilder, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ProductSave } from '../../../../models/product/ProductSave.model';
import { UOM } from 'src/app/models/uom/UOM.model';
import { UomService } from 'src/app/service/uom/uom.service';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.css']
})
export class ProductComponent implements OnInit {

  selectedProdCategory = {} as ProductCategory;
  productCategories = [] as ProductCategory[];
  productSubCategories = [] as ProductSubCategory[];
  uoms = [] as UOM[];
  
  constructor(
    private productService: ProductServiceService,
    private uomService: UomService,
    private formBuilder: FormBuilder,
    private modalService: BsModalService
  ) { }

  productForm = this.formBuilder.group({
    productCategory: ['', Validators.required],
    productSubCategory: ['', Validators.required],
    uom: ['', Validators.required],
    productName: ['', Validators.required],
    description: ['', Validators.required]
  });

  ngOnInit(): void {
    this.productService.getProductCategories().subscribe(response => {
      if(response.statusCode == 302) {
        this.productCategories = response.result;
      } else {
        this.showmodel
      }
    });
        
  }

  onSubmit() {
    let product = {} as ProductSave;
    product.description = this.productForm.value.description;
    product.productName = this.productForm.value.productName;
    product.uomID = 2;
    product.productSubCategory = this.productForm.value.productSubCategory;

    this.productService.saveProduct(product).subscribe(response => {
      if(response.statusCode = 201) {
        this.showmodel('Created Successfully');
        this.resetForm();
      } else {
        this.showmodel(response.message);
      }
    }, error => {
      this.showmodel('Failed Backend Connection');
    });

  }

  onProductCategorySelection(productCategory: ProductCategory) {
    this.productService.getProductSubCategories(productCategory.categoryId).subscribe(
      response => {
        if(response.statusCode == 302) {
          this.productSubCategories = response.result;
        }
      }
    );
  }

  showmodel(message: string) {
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

  resetForm() {
    this.productForm.reset();
  }

}
