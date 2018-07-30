import { Component, OnInit } from '@angular/core';
import {StratService} from '../services/strat.service';
import {Strategy} from "../models/strategy";
import {TwoMovingAverages} from "../models/twoMovingAverages";

@Component({
  selector: 'app-add-strat',
  templateUrl: './add-strat.component.html',
  styleUrls: ['./add-strat.component.css']
})
export class AddStratComponent implements OnInit {
  exitRuleDropdownText: string;
  isTwoMovingAverages: boolean;
  isBollingerBand: boolean;
  strategy = new TwoMovingAverages();
  //movingAveragesStrat: TwoMovingAverages;

  constructor(private addService: StratService) { }

  ngOnInit() {
    this.exitRuleDropdownText = "Exit Rule";
   this.isTwoMovingAverages = false;
   this.isBollingerBand = false;
  }

  addStrategy() {
    console.log("Form submitted");
    //this.movingAveragesStrat = new TwoMovingAverages(this.strategy.ticker, this.strategy.shortAverageSeconds, this.strategy.longAverageSeconds);
    this.addService.addStrat(this.strategy);
  }

  setTwoMovingAveragesForm() {
    this.isTwoMovingAverages = true;
    this.isBollingerBand = false;
  }

  setBollingerBandsForm() {
    this.isTwoMovingAverages = false;
    this.isBollingerBand = true;
  }

  setExitRule(rule: string) {
    this.strategy.exitRule = rule;
    this.exitRuleDropdownText = rule.toUpperCase();
  }

}
