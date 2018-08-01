import {Component, Input, OnInit} from '@angular/core';
import {StratService} from "../services/strat.service";
import {TradeService} from "../services/trade.service";
import {Strategy} from "../models/strategy";
import { Chart } from 'chart.js';
import {Order} from "../models/order";

@Component({
  selector: 'app-portfolio',
  templateUrl: './portfolio.component.html',
  styleUrls: ['./portfolio.component.css']
})
export class PortfolioComponent implements OnInit {
  strategies: any;
  stratObjs: Strategy[] = [];
  orderObjs: Order[] = [];
  trades: any;
  chartIsLoading: boolean = true;
  chart: Chart;

  constructor(private stratService: StratService, private tradeService: TradeService) { }

  ngOnInit() {
    this.genPortfolio();

  }

  genPortfolio() {
    this.stratService.getAll().subscribe( data => {
      this.strategies = data;
      this.stratObjs = [];
      for(let s of this.strategies) {
        this.stratObjs.push(s);
        //console.log(s.id);
      }

    });
  }


  getStrategyObj(id) {
    for(let s of this.stratObjs) {
      if(s.id == id) {
        return s;
      }
    }
  }

  disableStrat(id: String) {
    console.log("Trying to disable...");
    let s: Strategy = this.getStrategyObj(id);
    this.stratService.disableStrategy(id, s).subscribe( data => {
      //confirm on UI
      let d: any = data;
      s.exit = d.exit;

    });
/*    setTimeout(() => {
      this.updatePortfolio(id);
    }, 500);*/

  }

  expandStrat(id: String) {
    this.chartIsLoading = true;
    this.orderObjs = [];
    let pAndL =  [];
    let times = [];
    let o: Order;
    this.tradeService.getAllForStrat(id).subscribe( data => {
      this.trades = data;
        for(let t of this.trades) {
          o = t.order;
          o.timest = t.timeStamp.hour + ":" + t.timeStamp.minute;
          this.orderObjs.push(o);
          pAndL.push(t.profitAndLoss)
          times.push(o.timest);
        }
        if(this.chart !== undefined) {
          this.chart.destroy();
        }
      this.chart = new Chart('canvas', {
        type: 'line',
        data: {
          labels: times,
          datasets: [
            {
              data: pAndL,
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
          responsive: true,
          maintainAspectRatio: true
        }
      });

      }
    );
    this.chartIsLoading = false;
    this.scrollTo('topElement');

  }

  scrollTo(className: string):void {
    const elementList = document.querySelectorAll('.' + className);
    const element = elementList[0];
    element.scrollIntoView({ behavior: 'smooth' });
  }
}
