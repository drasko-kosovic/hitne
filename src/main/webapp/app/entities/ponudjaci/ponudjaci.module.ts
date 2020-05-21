import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HitneSharedModule } from 'app/shared/shared.module';
import { PonudjaciComponent } from './ponudjaci.component';
import { PonudjaciDetailComponent } from './ponudjaci-detail.component';
import { PonudjaciUpdateComponent } from './ponudjaci-update.component';
import { PonudjaciDeleteDialogComponent } from './ponudjaci-delete-dialog.component';
import { ponudjaciRoute } from './ponudjaci.route';

@NgModule({
  imports: [HitneSharedModule, RouterModule.forChild(ponudjaciRoute)],
  declarations: [PonudjaciComponent, PonudjaciDetailComponent, PonudjaciUpdateComponent, PonudjaciDeleteDialogComponent],
  entryComponents: [PonudjaciDeleteDialogComponent]
})
export class HitnePonudjaciModule {}
