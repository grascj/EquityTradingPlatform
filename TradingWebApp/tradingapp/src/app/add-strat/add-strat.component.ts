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
  errorMsg: string;
  isTwoMovingAverages: boolean;
  isBollingerBand: boolean;
  stratSelected: boolean = false;
  strategy: Strategy;
  displayError: boolean = false;
  @Output() idToAddChange = new EventEmitter<String>();


  constructor(private addService: StratService) { }

  ngOnInit() {
    this.errorMsg = "";
    this.displayError = false;
   this.isTwoMovingAverages = false;
   this.isBollingerBand = false;

  }

  addStrategy() {
    //Form validation
    if(this.strategy.exitRule == "loss" && this.strategy.exitPercentage > 0.2) {
      //implemet Citi cap
      this.strategy.exitRule = "loss";
      this.strategy.exitPercentage = 0.2;
      this.errorMsg = "Loss percentage too high...defaulting to 20% Loss cap";
      this.displayError = true;
    }
    if(this.strategy.exitRule == null || this.strategy.exitPercentage == null) {
      //user opted to not put an exit rule
      this.errorMsg = "No Exit Rule specified, defaulting to 20% Loss";
      this.displayError = true;
      //set default
      this.strategy.exitRule = "loss";
      this.strategy.exitPercentage = 0.2;
    }

    if(this.strategy.name == null) {
      this.strategy.name = "";
    }

    //api call
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

  closeErrorMsg() {
    this.errorMsg = "";
    this.displayError = false;
  }


}
