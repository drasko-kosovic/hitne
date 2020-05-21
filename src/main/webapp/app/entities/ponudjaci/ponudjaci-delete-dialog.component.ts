import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IPonudjaci } from 'app/shared/model/ponudjaci.model';
import { PonudjaciService } from './ponudjaci.service';

@Component({
  templateUrl: './ponudjaci-delete-dialog.component.html'
})
export class PonudjaciDeleteDialogComponent {
  ponudjaci?: IPonudjaci;

  constructor(protected ponudjaciService: PonudjaciService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.ponudjaciService.delete(id).subscribe(() => {
      this.eventManager.broadcast('ponudjaciListModification');
      this.activeModal.close();
    });
  }
}
