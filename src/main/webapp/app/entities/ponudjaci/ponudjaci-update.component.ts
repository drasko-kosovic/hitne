import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IPonudjaci, Ponudjaci } from 'app/shared/model/ponudjaci.model';
import { PonudjaciService } from './ponudjaci.service';
import { Validacija } from 'app/shared/custom-validators/validacija';

@Component({
  selector: 'jhi-ponudjaci-update',
  templateUrl: './ponudjaci-update.component.html'
})
export class PonudjaciUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    ponudjac: [null, [Validators.required]],
    // ponudjac: [null, [Validators.required],[ Validacija.cannotContainSpace]],
    kontakt: [],
    adresa: [],
    grad: [],
    telefon: [],
    email: [],
    postanskibroj: [],
    pib: [],
    fax: [],
    web: []
  });

  constructor(protected ponudjaciService: PonudjaciService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ ponudjaci }) => {
      this.updateForm(ponudjaci);
    });
  }

  updateForm(ponudjaci: IPonudjaci): void {
    this.editForm.patchValue({
      id: ponudjaci.id,
      ponudjac: ponudjaci.ponudjac,
      kontakt: ponudjaci.kontakt,
      adresa: ponudjaci.adresa,
      grad: ponudjaci.grad,
      telefon: ponudjaci.telefon,
      email: ponudjaci.email,
      postanskibroj: ponudjaci.postanskibroj,
      pib: ponudjaci.pib,
      fax: ponudjaci.fax,
      web: ponudjaci.web
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const ponudjaci = this.createFromForm();
    if (ponudjaci.id !== undefined) {
      this.subscribeToSaveResponse(this.ponudjaciService.update(ponudjaci));
    } else {
      this.subscribeToSaveResponse(this.ponudjaciService.create(ponudjaci));
    }
  }

  private createFromForm(): IPonudjaci {
    return {
      ...new Ponudjaci(),
      id: this.editForm.get(['id'])!.value,
      ponudjac: this.editForm.get(['ponudjac'])!.value,
      kontakt: this.editForm.get(['kontakt'])!.value,
      adresa: this.editForm.get(['adresa'])!.value,
      grad: this.editForm.get(['grad'])!.value,
      telefon: this.editForm.get(['telefon'])!.value,
      email: this.editForm.get(['email'])!.value,
      postanskibroj: this.editForm.get(['postanskibroj'])!.value,
      pib: this.editForm.get(['pib'])!.value,
      fax: this.editForm.get(['fax'])!.value,
      web: this.editForm.get(['web'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IPonudjaci>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
