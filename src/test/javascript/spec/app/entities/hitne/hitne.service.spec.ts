import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { HitneService } from 'app/entities/hitne/hitne.service';
import { IHitne, Hitne } from 'app/shared/model/hitne.model';

describe('Service Tests', () => {
  describe('Hitne Service', () => {
    let injector: TestBed;
    let service: HitneService;
    let httpMock: HttpTestingController;
    let elemDefault: IHitne;
    let expectedResult: IHitne | IHitne[] | boolean | null;
    let currentDate: moment.Moment;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(HitneService);
      httpMock = injector.get(HttpTestingController);
      currentDate = moment();

      elemDefault = new Hitne(0, 0, currentDate, 0, 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign(
          {
            datumpokretanja: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Hitne', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
            datumpokretanja: currentDate.format(DATE_FORMAT)
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datumpokretanja: currentDate
          },
          returnedFromService
        );

        service.create(new Hitne()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Hitne', () => {
        const returnedFromService = Object.assign(
          {
            brojpokretanja: 1,
            datumpokretanja: currentDate.format(DATE_FORMAT),
            vrijednost: 1,
            naziv: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datumpokretanja: currentDate
          },
          returnedFromService
        );

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Hitne', () => {
        const returnedFromService = Object.assign(
          {
            brojpokretanja: 1,
            datumpokretanja: currentDate.format(DATE_FORMAT),
            vrijednost: 1,
            naziv: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign(
          {
            datumpokretanja: currentDate
          },
          returnedFromService
        );

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Hitne', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
