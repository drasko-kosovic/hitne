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
  public resourceUrlReportHeroku = 'http://localhost:8080/';
  public resourceUrlReportLocal = 'http://localhost:8080/';

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

  findByBroj(broj: number): Observable<any> {
    return this.http
      .get<IHitne>(`${this.resourceUrl}?brojpokretanja.equals= ${broj}`, { observe: 'response' })
      .pipe(map((res: any) => this.convertDateFromServer(res)));
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

  printReportServicePokretanje(brojpokretanja: number | undefined): any {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.get<any>('http://localhost:8080/report/pokretanje/' + brojpokretanja, httpOptions);
  }

  printReportServiceOdluka(brojpokretanja: number | undefined): any {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.get<any>('http://localhost:8080/report/odluka/' + brojpokretanja, httpOptions);
  }
  printReportServiceZahtjev(brojpokretanja: number | undefined): any {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.get<any>('http://localhost:8080/report/zahtjev/' + brojpokretanja, httpOptions);
  }

  printServicePokretanje(brojpokretanja: number | undefined): any {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.get<any>('http://localhost:8080/report/print/pokretanje/' + brojpokretanja, httpOptions);
  }

  printServiceOdluka(brojpokretanja: number | undefined): any {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.get<any>('http://localhost:8080/report/print/odluka/' + brojpokretanja, httpOptions);
  }
  printServiceZahtjev(brojpokretanja: number | undefined): any {
    const httpOptions = {
      responseType: 'arraybuffer' as 'json'
      // 'responseType'  : 'blob' as 'json'        //This also worked
    };
    return this.http.get<any>('http://localhost:8080/report/print/zahtjev/' + brojpokretanja, httpOptions);
  }
}
