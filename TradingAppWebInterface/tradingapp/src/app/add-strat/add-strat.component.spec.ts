import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddStratComponent } from './add-strat.component';

describe('AddStratComponent', () => {
  let component: AddStratComponent;
  let fixture: ComponentFixture<AddStratComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddStratComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddStratComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
