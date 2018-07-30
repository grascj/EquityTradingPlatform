import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { AddStratComponent } from './add-strat/add-strat.component';
import {HttpClientModule} from "@angular/common/http";
import {AddStratService} from "./services/add-strat.service";
import {FormsModule} from "@angular/forms";
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
    FormsModule
  ],
  providers: [AddStratService],
  bootstrap: [AppComponent]
})
export class AppModule { }
