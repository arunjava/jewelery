import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, AbstractControl } from '@angular/forms';
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
import { Sales } from '../../../models/sales/Sales.model';
import { SalesService } from '../../../service/sales/sales.service';
import { CustomerScheme } from 'src/app/models/customer/CustomerScheme.model';
import { Scheme } from '../../../models/scheme/Scheme.model';

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
  customerSchemes = [] as CustomerScheme[];
  selectedCustomerScheme = {} as CustomerScheme;
  uom =  {} as UOM;
  selectedProductCat =  {} as ProductCategory;
  selectedProductSubCat = {} as ProductSubCategory;
  selectedProduct =  {} as Product;
  selectedUOM = {} as UOM;
  pricing  = {} as Pricing;
  sales = {} as Sales;

  constructor(
    private formBuilder: FormBuilder,
    private customerService: CustomerService,
    private modalService: BsModalService,
    private productService: ProductServiceService,
    private salesService: SalesService,
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
    wastageCharges: ['', Validators.required],
    makingCharges: ['', Validators.required],
    sgst: ['', Validators.required],
    cgst: ['', Validators.required],
    salesValue: ['', Validators.required],
  });

  get f() {
    return this.salesForm.controls;
  }

  onFormSubmit() {
    console.log(JSON.stringify(this.salesForm.value));
    this.sales.costPrice = this.salesForm.value.costPrice;
    this.sales.sellingPrice = this.salesForm.value.sellingPrice;
    this.sales.custID = this.customer.custId;
    this.sales.productID = this.selectedProduct.productId;
    // this.sales.uomID = this.selectedUOM.uom_id;
    this.sales.uomID = this.uom.uom_id;
    this.sales.qty = this.salesForm.value.qty;
    this.sales.makingCharges = this.salesForm.value.makingCharges;
    this.sales.wastageCharges = this.salesForm.value.wastageCharges;
    this.sales.sgst = Number(parseFloat(this.salesForm.value.sgst).toFixed(2));
    this.sales.cgst = Number(parseFloat(this.salesForm.value.cgst).toFixed(2));
    
    if(this.selectedCustomerScheme) {
      this.sales.customerSchemeID = this.selectedCustomerScheme.id;
    }
    console.log(JSON.stringify(this.sales));

    this.salesService.createSales(this.sales).subscribe(resp => {
      if(resp.statusCode == 201) {
        console.log(resp.result);
        this.showmodel('Transaction successful : ' + resp.result.invoiceNumber);
        this.resetValues();
      } else {
        console.log('Failed to update');
      }
    })
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

    //Get available schemes for selected Prod Category
    this.customerService.getListOfCustomerSchemesBsdOnProdCatID(this.customer.custId , productCatSel.categoryId).subscribe(
      response => {
        if(response.statusCode == 302) {
          this.customerSchemes = response.result;
        }
      }, error => {
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
    let wc: number = Number(this.salesForm.value.wastageCharges);
    let mc: number = Number(this.salesForm.value.makingCharges);
    let salesValue: number = (sp * qty) + wc + mc;
    let sgst: number = (salesValue * 1.5) / 100;
    let cgst: number = (salesValue * 1.5) / 100;

    salesValue = salesValue + sgst + cgst;
    this.salesForm.controls['sgst'].setValue(sgst.toFixed(2));
    this.salesForm.controls['cgst'].setValue(cgst.toFixed(2));
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

  selectedScheme(customerScheme : CustomerScheme) {
    console.log('Selected customer scheme ->' + JSON.stringify(customerScheme));
    let tempSale = {} as Sales;
    this.selectedCustomerScheme = customerScheme;
    this.sales.costPrice = this.salesForm.value.costPrice;
    this.sales.sellingPrice = this.salesForm.value.sellingPrice;
    this.sales.custID = this.customer.custId;
    this.sales.productID = this.selectedProduct.productId;
    this.uom.uom_id = customerScheme.uomExchangeID;
    this.sales.uomID = customerScheme.uomExchangeID;
    this.sales.qty = this.salesForm.value.qty;
    console.log(JSON.stringify(this.sales));

    this.salesService.getSalesDetails(this.sales).subscribe(resp => {
      if(resp.statusCode == 200) {
        console.log(resp.result);
        tempSale = resp.result;
        //Update form values
        this.salesForm.controls['qty'].setValue(customerScheme.uomExchangeVal);
        this.salesForm.controls['wastageCharges'].setValue(tempSale.wastageCharges);
        this.salesForm.controls['makingCharges'].setValue(tempSale.makingCharges);
        this.salesForm.controls['salesValue'].setValue(tempSale.soldAmt);
      } else {
        console.log('Failed to update');
      }
    });



  }

  resetValues(){
    this.customer = {} as Customer;
    let control: AbstractControl;
    this.salesForm.reset();
    this.salesForm.markAsUntouched();
    Object.keys(this.salesForm.controls).forEach((name) => {
      control = this.salesForm.controls[name];
      control.setErrors(null);
    });
    this.customerSchemes = [] as CustomerScheme[];
  }

  showmodel(message: string) {
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = 'Alert';
    modalRef.content.message = message;
  }

}
