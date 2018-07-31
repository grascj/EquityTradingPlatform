import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
//import {Trade} from "../models/strategy";

@Injectable()
export class TradeService {
  private baseUrl = 'http://localhost:8082';

  constructor(private http: HttpClient) { }

  getAll() {
    return this.http.get(this.baseUrl + '/performance/trades');
  }


}
