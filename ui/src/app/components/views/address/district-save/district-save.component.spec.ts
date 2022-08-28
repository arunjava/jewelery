import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DistrictSaveComponent } from './district-save.component';

describe('DistrictSaveComponent', () => {
  let component: DistrictSaveComponent;
  let fixture: ComponentFixture<DistrictSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DistrictSaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DistrictSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
