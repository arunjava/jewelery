import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SubDistrictSaveComponent } from './sub-district-save.component';

describe('SubDistrictSaveComponent', () => {
  let component: SubDistrictSaveComponent;
  let fixture: ComponentFixture<SubDistrictSaveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SubDistrictSaveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SubDistrictSaveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
