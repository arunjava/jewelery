import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ProductCategorySave } from 'src/app/models/product/ProductCategorySave.model';
import { ProductServiceService } from 'src/app/service/product-service/product-service.service';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from '../../../helper/modal/modal.component';

@Component({
  selector: 'app-product-category',
  templateUrl: './product-category.component.html',
  styleUrls: ['./product-category.component.css']
})
export class ProductCategoryComponent implements OnInit {

  productSaveCategory = {} as ProductCategorySave;

  constructor(
    private formBuilder: FormBuilder,
    private modalService: BsModalService,
    private productService: ProductServiceService) { }

  ngOnInit(): void {
  }

  productCatForm = this.formBuilder.group({
    categoryName: ['', Validators.required],
    description: ['', Validators.required]
  });

  onSubmit() {
    this.productSaveCategory.categoryName =this.productCatForm.value.categoryName;
    this.productSaveCategory.description = this.productCatForm.value.description;

    this.productService.saveProductCategory(this.productSaveCategory).subscribe(response => {
      if(response.statusCode == 201) {
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
    this.productCatForm.reset();
  }

}
