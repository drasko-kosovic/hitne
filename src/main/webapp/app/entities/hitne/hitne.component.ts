import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ActivatedRoute, Router } from '@angular/router';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { IHitne } from 'app/shared/model/hitne.model';

import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HitneService } from './hitne.service';
import { HitneDeleteDialogComponent } from './hitne-delete-dialog.component';

@Component({
  selector: 'jhi-hitne',
  templateUrl: './hitne.component.html'
})
export class HitneComponent implements OnInit, OnDestroy {
  hitnes?: IHitne[];
  eventSubscriber?: Subscription;
  totalItems = 0;
  itemsPerPage = ITEMS_PER_PAGE;
  page!: number;
  predicate!: string;
  ascending!: boolean;
  ngbPaginationPage = 1;
  brojPokretanja: any;

  constructor(
    protected hitneService: HitneService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadPage(page?: number): void {
    const pageToLoad: number = page || this.page;

    this.hitneService
      .query({
        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IHitne[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.page = data.pagingParams.page;
      this.ascending = data.pagingParams.ascending;
      this.predicate = data.pagingParams.predicate;
      this.ngbPaginationPage = data.pagingParams.page;
      this.loadPage();
    });
    this.registerChangeInHitnes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IHitne): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInHitnes(): void {
    this.eventSubscriber = this.eventManager.subscribe('hitneListModification', () => this.loadPage());
  }

  delete(hitne: IHitne): void {
    const modalRef = this.modalService.open(HitneDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.hitne = hitne;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected onSuccess(data: IHitne[] | null, headers: HttpHeaders, page: number): void {
    this.totalItems = Number(headers.get('X-Total-Count'));
    this.page = page;
    this.router.navigate(['/hitne'], {
      queryParams: {
        page: this.page,
        size: this.itemsPerPage,
        sort: this.predicate + ',' + (this.ascending ? 'asc' : 'desc')
      }
    });
    this.hitnes = data || [];
  }

  protected onError(): void {
    this.ngbPaginationPage = this.page;
  }
  prazansearch(): void {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    this.brojPokretanja = '';
    this.loadPage();
  }

  searchBrojPokretanja(page?: number): void {
    const pageToLoad: number = page ? page : this.page;
    this.hitneService
      .query({
        'brojpokretanja.equals': this.brojPokretanja,

        page: pageToLoad - 1,
        size: this.itemsPerPage,
        sort: this.sort()
      })
      .subscribe(
        (res: HttpResponse<IHitne[]>) => this.onSuccess(res.body, res.headers, pageToLoad),
        () => this.onError()
      );
  }
}
