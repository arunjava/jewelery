import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { CustomerService } from '../../../service/customer-service/customer.service';
import { Customer } from '../../../models/customer/customer.model';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from '../../helper/modal/modal.component';
import { ProductCategory } from '../../../models/product/ProductCategory.model';
import { ProductServiceService } from '../../../service/product-service/product-service.service';
import { ProductSubCategory } from '../../../models/product/ProductSubCategory.model';
import { Product } from '../../../models/product/Product.model';
import { UOM } from '../../../models/uom/UOM.model';
import { Pricing } from '../../../models/pricing/Pricing.model';
import { PricingService } from '../../../service/pricing-service/pricing.service';

@Component({
  selector: 'app-tx-sales',
  templateUrl: './tx-sales.component.html',
  styleUrls: ['./tx-sales.component.css']
})
export class TxSalesComponent implements OnInit {

  loading = false;
  customer!: Customer;
  productCategories = [] as ProductCategory[];
  productSubCategories = [] as ProductSubCategory[];
  products = [] as Product[];
  uom =  {} as UOM;
  selectedProductCat =  {} as ProductCategory;
  selectedProductSubCat = {} as ProductSubCategory;
  selectedProduct =  {} as Product;
  selectedUOM = {} as UOM;
  pricing  = {} as Pricing;

  constructor(
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
    private modalService: BsModalService,
    private productService: ProductServiceService,
    private pricingService: PricingService) { }

  ngOnInit(): void {
    //Load list of product categories
    this.productService.getProductCategories().subscribe(response => {
        if(response.statusCode == 302) {
          this.productCategories = response.result;
        }
    });
  }

  salesForm = this.formBuilder.group({
    customerName: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    productCategory: ['', Validators.required],
    productSubCategory: ['', Validators.required],
    product: ['', Validators.required],
    uom: ['', Validators.required],
    qty: ['', Validators.required],
    costPrice: ['', Validators.required],
    sellingPrice: ['', Validators.required],
    salesValue: ['', Validators.required]
  });

  onFormSubmit() {

  }

  onProductCatSelection(productCatSel : ProductCategory) {
    this.productSubCategories.splice(0);
    this.products.splice(0);
    this.productService.getProductSubCategories(productCatSel.categoryId).subscribe(
      response => {
         if(response.statusCode == 302) {
          this.productSubCategories = response.result;
         }
      }
    );
  }

  onProductSubCatSelection(productSubCatSel : ProductSubCategory) {
    this.productService.getProducts(productSubCatSel.subCategoryId).subscribe(
      response => {
         if(response.statusCode == 302) {
          this.products = response.result;
         }
      }
    );
  }

  onProductSelection(productSel : Product) {
    this.productService.getUOMByID(productSel.uomID).subscribe(response => {
      if(response.statusCode == 302) {
        this.uom = response.result;
      }
    });

    this.pricingService.getPricingBsdOnProdUOMID(productSel.productId , productSel.uomID).subscribe(
      resp => {
        if(resp.statusCode == 302) {
          this.pricing = resp.result;
          this.salesForm.controls['costPrice'].setValue(this.pricing.costPrice);
          this.salesForm.controls['sellingPrice'].setValue(this.pricing.salesPrice);
        }
      }
    );
  }

  onQtyUpdate() {
    this.updateSalesValue();
  }

  updateSalesValue() {
    let sp: number = this.salesForm.value.sellingPrice;
    let qty: number = this.salesForm.value.qty;
    let salesValue = sp * qty;
    this.salesForm.controls['salesValue'].setValue(salesValue.toFixed(2));
    console.log('Overall sales value -->' + salesValue);

  }


  getCustomerBsdOnPhoneNumber() {
    console.log(this.salesForm.value.phoneNumber);
    let phoneNum = this.salesForm.value.phoneNumber;
    if(phoneNum) {
       this.customerService.getCustomerBsdOnContactNumber(phoneNum).subscribe(response => {
          if(response.statusCode == 302) {
             this.customer = response.result;
             console.log(JSON.stringify(this.customer));
             this.salesForm.controls['customerName'].setValue(this.customer.customerName);
          } else {
            this.resetValues();
            this.showmodel('Phone number not found!');
          }
       });
    } else {
      this.resetValues();
     this.showmodel('Invalid Phone number!')
    }
  }

  resetValues(){
    this.customer = {} as Customer;
    this.salesForm.reset();
  }

  showmodel(message: string) {
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

}
