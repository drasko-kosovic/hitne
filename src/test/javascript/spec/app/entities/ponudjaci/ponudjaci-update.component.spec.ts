import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { HitneTestModule } from '../../../test.module';
import { PonudjaciUpdateComponent } from 'app/entities/ponudjaci/ponudjaci-update.component';
import { PonudjaciService } from 'app/entities/ponudjaci/ponudjaci.service';
import { Ponudjaci } from 'app/shared/model/ponudjaci.model';

describe('Component Tests', () => {
  describe('Ponudjaci Management Update Component', () => {
    let comp: PonudjaciUpdateComponent;
    let fixture: ComponentFixture<PonudjaciUpdateComponent>;
    let service: PonudjaciService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HitneTestModule],
        declarations: [PonudjaciUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(PonudjaciUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(PonudjaciUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(PonudjaciService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Ponudjaci(123);
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
        const entity = new Ponudjaci();
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
