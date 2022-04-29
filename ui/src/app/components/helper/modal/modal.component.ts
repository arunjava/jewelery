import { Component, OnInit, Injectable, Inject } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';


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
  public bsModalRef: BsModalRef) { }


}
