import {Component, OnInit} from '@angular/core';
import {StratService} from "../services/strat.service";
import {TradeService} from "../services/trade.service";
import {Strategy} from "../models/strategy";
import { Chart } from 'chart.js';

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
  strategies: any;
  stratObjs: Strategy[] = [];
  trades: any;

  chart = []

  constructor(private stratService: StratService, private tradeService: TradeService) { }

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
    let stratObjHolder = [];
    for(let s of this.stratObjs) {
      if(s.id != id) {
        stratObjHolder.push(s);
      }
    }
    console.log(this.stratObjs.length);
    console.log("Updating...");

     this.stratObjs =  stratObjHolder;
    console.log(this.stratObjs.length);
  }

  deleteStrat(id: String) {
    this.stratService.deleteStrategy(id);
    setTimeout(() => {
      this.updatePortfolio(id);
    }, 500);

  }

  expandStrat(id: String) {
    let prices =  [];
    this.tradeService.getAll().subscribe( data => {
      this.trades = data;
        for(let t of this.trades) {
          prices.push(t.order.price)
        }

      this.chart = new Chart('canvas', {
        type: 'line',
        data: {
          labels: [1, 2, 3, 4, 5, 6, 7, 8],
          datasets: [
            {
              data: prices,
              borderColor: "#3cba9f",
              fill: false
            }
          ]
        },
        options: {
          legend: {
            display: false
          },
          scales: {
            xAxes: [{
              display: true
            }],
            yAxes: [{
              display: true
            }],
          },
          maintainAspectRatio: false
        }
      });
      }
    );



  }
}
