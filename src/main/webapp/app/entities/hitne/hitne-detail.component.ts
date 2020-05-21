import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHitne } from 'app/shared/model/hitne.model';

@Component({
  selector: 'jhi-hitne-detail',
  templateUrl: './hitne-detail.component.html'
})
export class HitneDetailComponent implements OnInit {
  hitne: IHitne | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hitne }) => (this.hitne = hitne));
  }

  previousState(): void {
    window.history.back();
  }
}
