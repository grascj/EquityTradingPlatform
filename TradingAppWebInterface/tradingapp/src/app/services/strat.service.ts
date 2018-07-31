import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Headers, Http } from '@angular/http';
import {MarketUpdate} from '../models/marketUpdate'
import 'rxjs/add/operator/toPromise';

import { Observable } from 'rxjs/Observable';
import {TwoMovingAverages} from "../models/twoMovingAverages";
import {Strategy} from "../models/strategy";

@Injectable()
export class StratService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(this.baseUrl + '/api/strats');

  }

  addStrat(strategy: Strategy) {
    console.log("in servicew for add: " + strategy.ticker);
    return this.http.post(this.baseUrl + '/api/addStrat', strategy)
      .subscribe(res => {
        console.log("post executed with response");
      }, (err) => {
        console.log(err);
      });
  }

  deleteStrategy(id: String) {
    this.http.delete(this.baseUrl + '/api/deleteStrat/'+id) .subscribe(res => {
      console.log("post executed with response");
    }, (err) => {
      console.log(err);
    });
  }
}
