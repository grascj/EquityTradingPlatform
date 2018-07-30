import { TestBed, inject } from '@angular/core/testing';

import { AddStratService } from './add-strat.service';

describe('AddStratService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [AddStratService]
    });
  });

  it('should be created', inject([AddStratService], (service: AddStratService) => {
    expect(service).toBeTruthy();
  }));
});
