import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CustomerSchemeComponent } from './customer-scheme.component';

describe('CustomerSchemeComponent', () => {
  let component: CustomerSchemeComponent;
  let fixture: ComponentFixture<CustomerSchemeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CustomerSchemeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CustomerSchemeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
