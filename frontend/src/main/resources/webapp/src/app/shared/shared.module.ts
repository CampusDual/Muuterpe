import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { CommonModule } from '@angular/common';
import { BandsDetailComponent } from './bands-detail/bands-detail.component';
import { EventsDetailComponent } from './events-detail/events-detail.component';
import { SafePipe } from 'app/safe.pipe';



@NgModule({
  imports: [
    OntimizeWebModule
  ],
  declarations: [
    BandsDetailComponent,
    EventsDetailComponent,
    SafePipe
  ],
  exports: [
    CommonModule,
    BandsDetailComponent,
    EventsDetailComponent,
    SafePipe
  ]
})
export class SharedModule { }
