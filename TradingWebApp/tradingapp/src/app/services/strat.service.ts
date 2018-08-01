import { Injectable } from '@angular/core';
import {HttpClient } from '@angular/common/http';
import 'rxjs/add/operator/toPromise';

import { Observable } from 'rxjs/Observable';
import {TwoMovingAverages} from "../models/twoMovingAverages";
import {Strategy} from "../models/strategy";

@Injectable()
export class StratService {
  private baseUrl = 'http://localhost:8082';

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(this.baseUrl + '/api/strats');

  }

  addStrat(strategy: Strategy) {
    console.log("in servicew for add: " + strategy.ticker);
    return this.http.post(this.baseUrl + '/api/addStrat', strategy);
  }

  disableStrategy(id: String, strat: Strategy) {
    return this.http.put(this.baseUrl + '/api/disableStrat/'+id, strat);
  }
}
