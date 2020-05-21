import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Location } from '@angular/common';
import { IHitne, Hitne } from 'app/shared/model/hitne.model';
import { HitneService } from './hitne.service';
import { IPonudjaci } from 'app/shared/model/ponudjaci.model';
import { PonudjaciService } from 'app/entities/ponudjaci/ponudjaci.service';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-hitne-update',
  templateUrl: './hitne-update.component.html'
})
export class HitneUpdateComponent implements OnInit {
  isSaving = false;
  ponudjacis: IPonudjaci[] = [];
  datumpokretanjaDp: any;
  dodaj = false;

  editForm = this.fb.group({
    id: [],
    brojpokretanja: [null, [Validators.required]],
    datumpokretanja: [null, [Validators.required]],
    vrijednost: [null, [Validators.required]],
    naziv: [null, [Validators.required]],
    ponudjaci: []
  });

  constructor(
    protected hitneService: HitneService,
    protected ponudjaciService: PonudjaciService,
    protected activatedRoute: ActivatedRoute,
    protected router: Router,
    private fb: FormBuilder,
    public _location: Location,
    private alertService: JhiAlertService
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ hitne }) => {
      this.updateForm(hitne);

      this.ponudjaciService.query().subscribe((res: HttpResponse<IPonudjaci[]>) => (this.ponudjacis = res.body || []));
    });
  }

  updateForm(hitne: IHitne): void {
    this.editForm.patchValue({
      id: hitne.id,
      brojpokretanja: hitne.brojpokretanja,
      datumpokretanja: hitne.datumpokretanja,
      vrijednost: hitne.vrijednost,
      naziv: hitne.naziv,
      ponudjaci: hitne.ponudjaci
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const hitne = this.createFromForm();
    if (hitne.id !== undefined) {
      this.subscribeToSaveResponse(this.hitneService.update(hitne));
    } else {
      this.subscribeToSaveResponse(this.hitneService.create(hitne));
    }
  }

  private createFromForm(): IHitne {
    return {
      ...new Hitne(),
      id: this.editForm.get(['id'])!.value,
      brojpokretanja: this.editForm.get(['brojpokretanja'])!.value,
      datumpokretanja: this.editForm.get(['datumpokretanja'])!.value,
      vrijednost: this.editForm.get(['vrijednost'])!.value,
      naziv: this.editForm.get(['naziv'])!.value,
      ponudjaci: this.editForm.get(['ponudjaci'])!.value
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IHitne>>): void {
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

  trackById(index: number, item: IPonudjaci): any {
    return item.id;
  }

  printReportZahtjev(): any {
    this.hitneService.printReportServiceZahtjev(this.editForm.get(['brojpokretanja'])!.value).subscribe((response: BlobPart) => {
      const file = new Blob([response], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL);
      this.dodaj = true;
    });
  }

  printReportPokretanje(): any {
    this.hitneService.printReportServicePokretanje(this.editForm.get(['brojpokretanja'])!.value).subscribe((response: BlobPart) => {
      const file = new Blob([response], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL);
      this.dodaj = true;
    });
  }

  printReportOdluka(): any {
    this.hitneService.printReportServiceOdluka(this.editForm.get(['brojpokretanja'])!.value).subscribe((response: BlobPart) => {
      const file = new Blob([response], { type: 'application/pdf' });
      const fileURL = URL.createObjectURL(file);
      window.open(fileURL);
      this.dodaj = true;
    });
  }

  refresh(): void {
    this.router.navigateByUrl('/new', { skipLocationChange: true }).then(() => {
      // console.log(decodeURI(this._location.path()));

      this.router.navigate([decodeURI(this._location.path())]);
    });
  }
}
