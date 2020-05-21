import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { HitneSharedModule } from 'app/shared/shared.module';
import { HitneCoreModule } from 'app/core/core.module';
import { HitneAppRoutingModule } from './app-routing.module';
import { HitneHomeModule } from './home/home.module';
import { HitneEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ActiveMenuDirective } from './layouts/navbar/active-menu.directive';
import { ErrorComponent } from './layouts/error/error.component';
import { TextareaAutosizeModule } from 'ngx-textarea-autosize';

@NgModule({
  imports: [
    BrowserModule,
    HitneSharedModule,
    HitneCoreModule,
    HitneHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    HitneEntityModule,
    HitneAppRoutingModule,
    TextareaAutosizeModule
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, ActiveMenuDirective, FooterComponent],
  bootstrap: [MainComponent]
})
export class HitneAppModule {}
