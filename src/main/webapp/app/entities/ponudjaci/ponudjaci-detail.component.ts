import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IPonudjaci } from 'app/shared/model/ponudjaci.model';

@Component({
  selector: 'jhi-ponudjaci-detail',
  templateUrl: './ponudjaci-detail.component.html'
})
export class PonudjaciDetailComponent implements OnInit {
  ponudjaci: IPonudjaci | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ponudjaci }) => (this.ponudjaci = ponudjaci));
  }

  previousState(): void {
    window.history.back();
  }
}
