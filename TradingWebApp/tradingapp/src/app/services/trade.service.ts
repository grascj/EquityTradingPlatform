import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
//import {Trade} from "../models/strategy";

@Injectable()
export class TradeService {
  private baseUrl = '';

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(this.baseUrl + '/performance/trades');
  }

  getForStrat(id: String) {
    return this.http.get(this.baseUrl + '/performance/recentTrades/' + id);
  }

  getAllForStrat(id: String) {
    return this.http.get(this.baseUrl + '/performance/trades/' + id);
  }
}
