import { TestBed, inject } from '@angular/core/testing';

import { StratService } from './strat.service';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

describe('StratService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        NgbModule.forRoot()],
      providers: [StratService]
    });
  });

  it('should be created', inject([StratService], (service: StratService) => {
    expect(service).toBeTruthy();
  }));


});
