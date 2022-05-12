import { Component, OnInit, Injectable, Inject, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { ModalBox } from '../../../models/ModalBox.model';


@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css']
})
export class ModalComponent implements OnInit {

  ngOnInit(): void {
  }

  title: string = '';
  message: string = '';

  constructor(@Inject(BsModalRef)
  public bsModalRef: BsModalRef,
    private modalService: BsModalService) { }

  openModal(template: TemplateRef<any>) {
    this.bsModalRef = this.modalService.show(template);
  }

}
