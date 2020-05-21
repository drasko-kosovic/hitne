import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHitne } from 'app/shared/model/hitne.model';
import { HitneService } from './hitne.service';

@Component({
  templateUrl: './hitne-delete-dialog.component.html'
})
export class HitneDeleteDialogComponent {
  hitne?: IHitne;

  constructor(protected hitneService: HitneService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.hitneService.delete(id).subscribe(() => {
      this.eventManager.broadcast('hitneListModification');
      this.activeModal.close();
    });
  }
}
