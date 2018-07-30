import { Component, OnInit } from '@angular/core';
import {AddStratService} from '../services/add-strat.service';
import {Strategy} from "../models/strategy";
import {TwoMovingAverages} from "../models/twoMovingAverages";

@Component({
  selector: 'app-add-strat',
  templateUrl: './add-strat.component.html',
  styleUrls: ['./add-strat.component.css']
})
export class AddStratComponent implements OnInit {
  datapoints: Array<any>;
  strategy = new TwoMovingAverages();
  //movingAveragesStrat: TwoMovingAverages;

  constructor(private addService: AddStratService) { }

  ngOnInit() {
    //this.addService.getAll().subscribe(data => this.datapoints = data);
  }

  addStrategy() {
    console.log("Form submitted");
    //this.movingAveragesStrat = new TwoMovingAverages(this.strategy.ticker, this.strategy.shortAverageSeconds, this.strategy.longAverageSeconds);
    this.addService.addStrat(this.strategy);
  }

}
