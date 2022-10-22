import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from 'src/app/components/helper/modal/modal.component';
import { Product } from 'src/app/models/product/Product.model';
import { ProductCategory } from 'src/app/models/product/ProductCategory.model';
import { ProductSave } from 'src/app/models/product/ProductSave.model';
import { ProductSubCategory } from 'src/app/models/product/ProductSubCategory.model';
import { UOM } from 'src/app/models/uom/UOM.model';
import { ProductServiceService } from 'src/app/service/product-service/product-service.service';
import { UomService } from 'src/app/service/uom/uom.service';
import { Pricing } from '../../../../models/pricing/Pricing.model';

@Component({
  selector: 'app-product-pricing',
  templateUrl: './product-pricing.component.html',
  styleUrls: ['./product-pricing.component.css']
})
export class ProductPricingComponent implements OnInit {
  selectedProdCategory = {} as ProductCategory;
  selectedProdSubCategory = {} as ProductSubCategory;
  productCategories = [] as ProductCategory[];
  productSubCategories = [] as ProductSubCategory[];
  products = [] as Product[];
  uoms = [] as UOM[];

  constructor(
    private productService: ProductServiceService,
    private uomService: UomService,
    private formBuilder: FormBuilder,
    private modalService: BsModalService
  ) { }

  productPricingForm = this.formBuilder.group({
    productCategory: ['', Validators.required],
    productSubCategory: ['', Validators.required],
    // uom: ['', Validators.required],
    productName: ['', Validators.required],
    costPrice: ['', Validators.required],
    sellingPrice: ['', Validators.required],
    mrp: ['', Validators.required]
  });

  // convenience getter for easy access to form fields
  get f() { return this.productPricingForm.controls; }

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
    let pricing = {} as Pricing;
    
    pricing.costPrice = this.productPricingForm.value.costPrice;
    pricing.salesPrice = this.productPricingForm.value.sellingPrice;
    pricing.mrp = this.productPricingForm.value.mrp;
    pricing.productID = this.productPricingForm.value.productName.productId;
    pricing.uomID = 102;
    pricing.isActive = true;
    
    console.log(JSON.stringify(pricing));

    this.productService.saveProductPricing(pricing).subscribe(response => {
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

  onProductSubCategorySelection(productSubCategory: ProductSubCategory) {
    this.productService.getProducts(productSubCategory.subCategoryId).subscribe(
      response => {
        if(response.statusCode == 302) {
          this.products = response.result;
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
    this.productPricingForm.reset();
  }

}
