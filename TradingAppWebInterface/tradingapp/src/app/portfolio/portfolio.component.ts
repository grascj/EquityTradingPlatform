import {Component, OnInit} from '@angular/core';
import {StratService} from "../services/strat.service";
import {MongoId} from "../models/mongoId";
import {Strategy} from "../models/strategy";

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
  strategies: any;
  stratObjs: Strategy[] = [];

  constructor(private stratService: StratService) { }

  ngOnInit() {
    this.stratService.getAll().subscribe( data => {
      this.strategies = data;
      this.stratObjs = [];
      for(let s of this.strategies) {
        this.stratObjs.push(s);
        //console.log(s.id);
      }

    });

  }

  updatePortfolio(id) {
    for(let s of this.stratObjs) {
      console.log(s.id + " and id " + id + " match?? ");
      console.log(s.id != id);
    }
    console.log("Updating...");
      this.stratObjs.filter((elem) => {
        return elem.id != id;
      });
  }

  deleteStrat(id: String) {
    this.stratService.deleteStrategy(id);
    setTimeout(() => {
      this.updatePortfolio(id);
    }, 1000);

  }

  expandStrat(id: String) {
  //TODO
  }
}
