import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { HitneTestModule } from '../../../test.module';
import { HitneUpdateComponent } from 'app/entities/hitne/hitne-update.component';
import { HitneService } from 'app/entities/hitne/hitne.service';
import { Hitne } from 'app/shared/model/hitne.model';

describe('Component Tests', () => {
  describe('Hitne Management Update Component', () => {
    let comp: HitneUpdateComponent;
    let fixture: ComponentFixture<HitneUpdateComponent>;
    let service: HitneService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HitneTestModule],
        declarations: [HitneUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(HitneUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(HitneUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(HitneService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Hitne(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Hitne();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
