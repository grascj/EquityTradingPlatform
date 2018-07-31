import { Component, OnInit } from '@angular/core';
import {StratService} from '../services/strat.service';
import {Strategy} from "../models/strategy";
import {TwoMovingAverages} from "../models/twoMovingAverages";
import {BollingerBands} from "../models/bollingerBands";
import {FormControl, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-strat',
  templateUrl: './add-strat.component.html',
  styleUrls: ['./add-strat.component.css']
})
export class AddStratComponent implements OnInit {
  exitRuleDropdownText: string;
  isTwoMovingAverages: boolean;
  isBollingerBand: boolean;
  stratSelected: boolean = false;
  strategy: Strategy;
  isFormValid: boolean = false;


  //movingAveragesStrat: TwoMovingAverages;

  constructor(private addService: StratService) { }

  ngOnInit() {
    this.exitRuleDropdownText = "Exit Rule";
   this.isTwoMovingAverages = false;
   this.isBollingerBand = false;

  }

  addStrategy() {
    console.log("Form submitted");
    this.addService.addStrat(this.strategy);
    //reset
    this.stratSelected = false;
    this.exitRuleDropdownText = "Exit Rule";
    this.isFormValid = false;

    if(this.isFormValid) {

    }


  }

  setTwoMovingAveragesForm() {
    this.stratSelected = true;
    this.isTwoMovingAverages = true;
    this.isBollingerBand = false;
    this.strategy = new TwoMovingAverages();
  }

  setBollingerBandsForm() {
    this.stratSelected = true;
    this.isTwoMovingAverages = false;
    this.isBollingerBand = true;
    this.strategy = new BollingerBands();
  }

  setExitRule(rule: string) {
    this.strategy.exitRule = rule;
    this.exitRuleDropdownText = rule.toUpperCase();
  }

}
