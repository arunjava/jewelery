import { Component, OnInit } from '@angular/core';
import { Scheme } from 'src/app/models/scheme/Scheme.model';
import { SchemeService } from 'src/app/service/scheme/scheme.service';
import { BsModalService } from 'ngx-bootstrap/modal';
import { ModalComponent } from 'src/app/components/helper/modal/modal.component';

@Component({
  selector: 'app-scheme-view',
  templateUrl: './scheme-view.component.html',
  styleUrls: ['./scheme-view.component.css']
})
export class SchemeViewComponent implements OnInit {

  schemes = [] as Scheme[];
  public isCollapsed = true;

  constructor(
    private modalService: BsModalService,
    private schemeService: SchemeService) { }

  ngOnInit(): void {
    this.schemeService.getAllActiveSchemes().subscribe(response => {
      if(response.statusCode = 302) {
        this.schemes = response.result;
      } else {

      }
    }, error => {
      console.log(error.statusText);
    });
  }

  showmodel(message: string, title: string) {
    console.log('Inside modal dispaly');
    console.log(message);
    let modalRef: any = this.modalService.show(ModalComponent, { class: 'modal-lg' });
    modalRef.content.title = title;
    modalRef.content.message = message;
  }

  // updateCollapse() {
  //   this.collapseEnabled = false;
  // }

}
