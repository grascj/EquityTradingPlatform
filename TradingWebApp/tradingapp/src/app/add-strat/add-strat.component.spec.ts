import {async, ComponentFixture, inject, TestBed} from '@angular/core/testing';

import { AddStratComponent } from './add-strat.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {StratService} from "../services/strat.service";
import {TradeService} from "../services/trade.service";
import {NgbDropdown} from "@ng-bootstrap/ng-bootstrap";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {HttpTestingController} from "@angular/common/http/testing";

describe('AddStratComponent', () => {
  let component: AddStratComponent;
  let fixture: ComponentFixture<AddStratComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,
        ReactiveFormsModule,
        HttpClientModule],
      declarations: [ AddStratComponent ],
      providers: [StratService, NgbDropdown]
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

  it('should initialize corretly', () => {
    expect(component.isBollingerBand).toBeFalsy();
    expect(component.isTwoMovingAverages).toBeFalsy();
  });
  /*describe(`FakeHttpClientResponses`, () => {

    it(`should expect a GET /api/strats`, async(inject([HttpClient, HttpTestingController],
    (http: HttpClient, backend: HttpTestingController) => {
      http.get('/api/strats').subscribe();

      backend.expectOne({
        url: '/api/strats',
        method: 'GET'
      });
    }))); });*/

  it('should set bollinger form', () => {

    component.setBollingerBandsForm();
    expect(component.stratSelected).toBeTruthy();
    expect(component.isBollingerBand).toBeTruthy();
    expect(component.isTwoMovingAverages).toBeFalsy();

  });

  it('should set two moving avgs form', () => {

    component.setTwoMovingAveragesForm();
    expect(component.stratSelected).toBeTruthy();
    expect(component.isBollingerBand).toBeFalsy();
    expect(component.isTwoMovingAverages).toBeTruthy();

  });
});
