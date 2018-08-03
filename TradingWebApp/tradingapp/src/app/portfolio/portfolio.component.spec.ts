import {async, ComponentFixture, inject, TestBed} from '@angular/core/testing';

import { PortfolioComponent } from './portfolio.component';
import {StratService} from "../services/strat.service";
import {NgbDropdown, NgbModal, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {TradeService} from "../services/trade.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpTestingController} from "@angular/common/http/testing";

describe('PortfolioComponent', () => {
  let component: PortfolioComponent;
  let fixture: ComponentFixture<PortfolioComponent>;
  let sService: StratService;
  let tService: TradeService;
  let modal: NgbModal;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        NgbModule.forRoot()],
      declarations: [ PortfolioComponent ],
      providers: [StratService, TradeService, NgbDropdown]
    })
    .compileComponents();
  }));

  beforeEach(() => {

    fixture = TestBed.createComponent(PortfolioComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });


/*
  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should ', async(inject([HttpClient, HttpTestingController],
    (http: HttpClient, backend: HttpTestingController) => {
    this.genPortfolio();
  });*/
});
