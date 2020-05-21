import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { HitneTestModule } from '../../../test.module';
import { HitneDetailComponent } from 'app/entities/hitne/hitne-detail.component';
import { Hitne } from 'app/shared/model/hitne.model';

describe('Component Tests', () => {
  describe('Hitne Management Detail Component', () => {
    let comp: HitneDetailComponent;
    let fixture: ComponentFixture<HitneDetailComponent>;
    const route = ({ data: of({ hitne: new Hitne(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HitneTestModule],
        declarations: [HitneDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(HitneDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(HitneDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load hitne on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.hitne).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
