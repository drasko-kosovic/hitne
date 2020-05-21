import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { IPonudjaci } from 'app/shared/model/ponudjaci.model';

type EntityResponseType = HttpResponse<IPonudjaci>;
type EntityArrayResponseType = HttpResponse<IPonudjaci[]>;

@Injectable({ providedIn: 'root' })
export class PonudjaciService {
  public resourceUrl = SERVER_API_URL + 'api/ponudjacis';

  constructor(protected http: HttpClient) {}

  create(ponudjaci: IPonudjaci): Observable<EntityResponseType> {
    return this.http.post<IPonudjaci>(this.resourceUrl, ponudjaci, { observe: 'response' });
  }

  update(ponudjaci: IPonudjaci): Observable<EntityResponseType> {
    return this.http.put<IPonudjaci>(this.resourceUrl, ponudjaci, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<IPonudjaci>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<IPonudjaci[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
