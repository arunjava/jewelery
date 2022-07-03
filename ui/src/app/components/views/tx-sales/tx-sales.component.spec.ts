import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TxSalesComponent } from './tx-sales.component';

describe('TxSalesComponent', () => {
  let component: TxSalesComponent;
  let fixture: ComponentFixture<TxSalesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TxSalesComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TxSalesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
