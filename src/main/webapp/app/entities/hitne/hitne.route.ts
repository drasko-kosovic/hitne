import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IHitne, Hitne } from 'app/shared/model/hitne.model';
import { HitneService } from './hitne.service';
import { HitneComponent } from './hitne.component';
import { HitneDetailComponent } from './hitne-detail.component';
import { HitneUpdateComponent } from './hitne-update.component';

@Injectable({ providedIn: 'root' })
export class HitneResolve implements Resolve<IHitne> {
  constructor(private service: HitneService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IHitne> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((hitne: HttpResponse<Hitne>) => {
          if (hitne.body) {
            return of(hitne.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Hitne());
  }
}

export const hitneRoute: Routes = [
  {
    path: '',
    component: HitneComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'hitneApp.hitne.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: HitneDetailComponent,
    resolve: {
      hitne: HitneResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'hitneApp.hitne.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: HitneUpdateComponent,
    resolve: {
      hitne: HitneResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'hitneApp.hitne.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: HitneUpdateComponent,
    resolve: {
      hitne: HitneResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'hitneApp.hitne.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
