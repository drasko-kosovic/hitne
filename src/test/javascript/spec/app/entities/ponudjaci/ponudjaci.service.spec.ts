import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { PonudjaciService } from 'app/entities/ponudjaci/ponudjaci.service';
import { IPonudjaci, Ponudjaci } from 'app/shared/model/ponudjaci.model';

describe('Service Tests', () => {
  describe('Ponudjaci Service', () => {
    let injector: TestBed;
    let service: PonudjaciService;
    let httpMock: HttpTestingController;
    let elemDefault: IPonudjaci;
    let expectedResult: IPonudjaci | IPonudjaci[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule]
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(PonudjaciService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new Ponudjaci(
        0,
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA',
        'AAAAAAA'
      );
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a Ponudjaci', () => {
        const returnedFromService = Object.assign(
          {
            id: 0
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new Ponudjaci()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a Ponudjaci', () => {
        const returnedFromService = Object.assign(
          {
            ponudjac: 'BBBBBB',
            kontakt: 'BBBBBB',
            adresa: 'BBBBBB',
            grad: 'BBBBBB',
            telefon: 'BBBBBB',
            email: 'BBBBBB',
            postanskibroj: 'BBBBBB',
            pib: 'BBBBBB',
            fax: 'BBBBBB',
            web: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of Ponudjaci', () => {
        const returnedFromService = Object.assign(
          {
            ponudjac: 'BBBBBB',
            kontakt: 'BBBBBB',
            adresa: 'BBBBBB',
            grad: 'BBBBBB',
            telefon: 'BBBBBB',
            email: 'BBBBBB',
            postanskibroj: 'BBBBBB',
            pib: 'BBBBBB',
            fax: 'BBBBBB',
            web: 'BBBBBB'
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a Ponudjaci', () => {
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
