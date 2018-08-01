import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {StratService} from '../services/strat.service';
import {Strategy} from "../models/strategy";
import {TwoMovingAverages} from "../models/twoMovingAverages";
import {BollingerBands} from "../models/bollingerBands";

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
  @Output() idToAddChange = new EventEmitter<String>();


  constructor(private addService: StratService) { }

  ngOnInit() {
    this.exitRuleDropdownText = "Exit Rule";
   this.isTwoMovingAverages = false;
   this.isBollingerBand = false;

  }

  addStrategy() {
    console.log("Form submitted");
    this.addService.addStrat(this.strategy)
      .subscribe(data => {
        let s: any = data;
        //inidcate to parent component that a new strategy had been added
        this.idToAddChange.emit(s.id);
      }, (err) => {
        console.log(err);
      });

    //reset
    this.stratSelected = false;



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


}
