import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { HitneSharedModule } from 'app/shared/shared.module';
import { HitneComponent } from './hitne.component';
import { HitneDetailComponent } from './hitne-detail.component';
import { HitneUpdateComponent } from './hitne-update.component';
import { HitneDeleteDialogComponent } from './hitne-delete-dialog.component';
import { hitneRoute } from './hitne.route';
import { TextareaAutosizeModule } from 'ngx-textarea-autosize';

@NgModule({
  imports: [HitneSharedModule, RouterModule.forChild(hitneRoute), TextareaAutosizeModule],
  declarations: [HitneComponent, HitneDetailComponent, HitneUpdateComponent, HitneDeleteDialogComponent],
  entryComponents: [HitneDeleteDialogComponent]
})
export class HitneHitneModule {}
