import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { AddStratComponent } from './add-strat/add-strat.component';
import {HttpClientModule} from "@angular/common/http";
import {StratService} from "./services/strat.service";
import { ReactiveFormsModule, FormsModule} from "@angular/forms";
import { PortfolioComponent } from './portfolio/portfolio.component';


@NgModule({
  declarations: [
    AppComponent,
    AddStratComponent,
    PortfolioComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    NgbModule.forRoot()
  ],
  providers: [StratService],
  bootstrap: [AppComponent]
})
export class AppModule { }
