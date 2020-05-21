import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IHitne } from 'app/shared/model/hitne.model';

type EntityResponseType = HttpResponse<IHitne>;
type EntityArrayResponseType = HttpResponse<IHitne[]>;

@Injectable({ providedIn: 'root' })
export class HitneService {
  public resourceUrl = SERVER_API_URL + 'api/hitnes';

  constructor(protected http: HttpClient) {}

  create(hitne: IHitne): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hitne);
    return this.http
      .post<IHitne>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  update(hitne: IHitne): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(hitne);
    return this.http
      .put<IHitne>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IHitne>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IHitne[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  protected convertDateFromClient(hitne: IHitne): IHitne {
    const copy: IHitne = Object.assign({}, hitne, {
      datumpokretanja: hitne.datumpokretanja && hitne.datumpokretanja.isValid() ? hitne.datumpokretanja.format(DATE_FORMAT) : undefined
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datumpokretanja = res.body.datumpokretanja ? moment(res.body.datumpokretanja) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((hitne: IHitne) => {
        hitne.datumpokretanja = hitne.datumpokretanja ? moment(hitne.datumpokretanja) : undefined;
      });
    }
    return res;
  }
}
