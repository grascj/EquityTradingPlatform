import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from '@angular/common/http';
import { Headers, Http } from '@angular/http';
import {MarketUpdate} from '../models/marketUpdate'
import 'rxjs/add/operator/toPromise';

import { Observable } from 'rxjs/Observable';
import {TwoMovingAverages} from "../models/twoMovingAverages";

@Injectable()
export class AddStratService {
  private baseUrl = 'http://localhost:8081';

  constructor(private http: HttpClient) { }

  getAll(): Observable<any> {
    return this.http.get(this.baseUrl + '/api/updates');
      //.map(response => response.json());
      //.catch(this.handleError);
  }

  addStrat(strategy: TwoMovingAverages) {
    console.log("in servicew for add: " + strategy.ticker + " " + strategy.longAverageSeconds + " " + strategy.shortAverageSeconds);
    return this.http.post(this.baseUrl + '/api/addStrat', strategy)
      .subscribe(res => {
        console.log("post executed with response");
      }, (err) => {
        console.log(err);
      });
  }
}
