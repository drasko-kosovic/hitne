import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { JhiResolvePagingParams } from 'ng-jhipster';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { IPonudjaci, Ponudjaci } from 'app/shared/model/ponudjaci.model';
import { PonudjaciService } from './ponudjaci.service';
import { PonudjaciComponent } from './ponudjaci.component';
import { PonudjaciDetailComponent } from './ponudjaci-detail.component';
import { PonudjaciUpdateComponent } from './ponudjaci-update.component';

@Injectable({ providedIn: 'root' })
export class PonudjaciResolve implements Resolve<IPonudjaci> {
  constructor(private service: PonudjaciService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IPonudjaci> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((ponudjaci: HttpResponse<Ponudjaci>) => {
          if (ponudjaci.body) {
            return of(ponudjaci.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Ponudjaci());
  }
}

export const ponudjaciRoute: Routes = [
  {
    path: '',
    component: PonudjaciComponent,
    resolve: {
      pagingParams: JhiResolvePagingParams
    },
    data: {
      authorities: [Authority.USER],
      defaultSort: 'id,asc',
      pageTitle: 'hitneApp.ponudjaci.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/view',
    component: PonudjaciDetailComponent,
    resolve: {
      ponudjaci: PonudjaciResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'hitneApp.ponudjaci.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: 'new',
    component: PonudjaciUpdateComponent,
    resolve: {
      ponudjaci: PonudjaciResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'hitneApp.ponudjaci.home.title'
    },
    canActivate: [UserRouteAccessService]
  },
  {
    path: ':id/edit',
    component: PonudjaciUpdateComponent,
    resolve: {
      ponudjaci: PonudjaciResolve
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'hitneApp.ponudjaci.home.title'
    },
    canActivate: [UserRouteAccessService]
  }
];
