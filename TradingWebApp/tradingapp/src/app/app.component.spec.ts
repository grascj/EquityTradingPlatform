import {TestBed, async, ComponentFixture, inject} from '@angular/core/testing';
import { AppComponent } from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {PortfolioComponent} from "./portfolio/portfolio.component";
import {AddStratComponent} from "./add-strat/add-strat.component";
import {StratService} from "./services/strat.service";
import {TradeService} from "./services/trade.service";
import {NgbDropdown, NgbModule} from "@ng-bootstrap/ng-bootstrap";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {HttpClientTestingModule, HttpTestingController} from "@angular/common/http/testing";
const stratsData  = require('./mockStratData.json');

describe('AppComponent', () => {
  let app: AppComponent;
  let fixture : ComponentFixture<AppComponent>;
  beforeEach(async(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientTestingModule,
        NgbModule.forRoot()],
      declarations: [
        AppComponent,
    AddStratComponent,
      PortfolioComponent
      ],
      providers: [StratService, TradeService, NgbDropdown]
    }).compileComponents().then(
      () => {
        /*spyOn(stratService, 'fetchData').and.returnValue(Promise.resolve(stratsData));*/
        fixture = TestBed.createComponent(AppComponent);
        app = fixture.debugElement.componentInstance;
        fixture.detectChanges();
      }
    );
  }));

  describe(`FakeHttpClientResponses`, () => {


  it('should create the app', async(() => {
    expect(app).toBeTruthy();
  }));
  it(`should have as title 'app'`, async(() => {
    const fixture = TestBed.createComponent(AppComponent);
    const app = fixture.debugElement.componentInstance;
    expect(app.title).toEqual('Equities Trading Platform');
  }));

});});
