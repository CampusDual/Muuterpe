import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { CommonModule } from '@angular/common';
import { BandsDetailComponent } from './bands-detail/bands-detail.component';
import { EventsDetailComponent } from './events-detail/events-detail.component';
import { SafePipe } from 'app/safe.pipe';
import { RouterModule } from '@angular/router';
 
@NgModule({
  imports: [
    OntimizeWebModule,
    RouterModule 
  ],
  declarations: [
    BandsDetailComponent,
    EventsDetailComponent,
    SafePipe
  ],
  exports: [
    CommonModule,
    BandsDetailComponent,
    EventsDetailComponent
  ]
})
export class SharedModule { }