import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HitneTestModule } from '../../../test.module';
import { PonudjaciDetailComponent } from 'app/entities/ponudjaci/ponudjaci-detail.component';
import { Ponudjaci } from 'app/shared/model/ponudjaci.model';

describe('Component Tests', () => {
  describe('Ponudjaci Management Detail Component', () => {
    let comp: PonudjaciDetailComponent;
    let fixture: ComponentFixture<PonudjaciDetailComponent>;
    const route = ({ data: of({ ponudjaci: new Ponudjaci(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HitneTestModule],
        declarations: [PonudjaciDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(PonudjaciDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(PonudjaciDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load ponudjaci on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.ponudjaci).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
