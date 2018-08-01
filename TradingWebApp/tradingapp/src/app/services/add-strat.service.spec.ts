import { TestBed, inject } from '@angular/core/testing';

import { StratService } from './strat.service';

describe('StratService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [StratService]
    });
  });

  it('should be created', inject([StratService], (service: StratService) => {
    expect(service).toBeTruthy();
  }));
});
