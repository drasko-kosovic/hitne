import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'hitne',
        loadChildren: () => import('./hitne/hitne.module').then(m => m.HitneHitneModule)
      },
      {
        path: 'ponudjaci',
        loadChildren: () => import('./ponudjaci/ponudjaci.module').then(m => m.HitnePonudjaciModule)
      }
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ])
  ]
})
export class HitneEntityModule {}
