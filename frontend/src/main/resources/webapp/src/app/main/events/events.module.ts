import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from "ontimize-web-ngx";
import { EventsRoutingModule } from './events-routing.module';
import { EventsHomeComponent } from './events-home/events-home.component';
import { SharedModule } from 'app/shared/shared.module';
import { EventsDetailComponent } from 'app/shared/events-detail/events-detail.component';

@NgModule({
  imports: [
    CommonModule,
    OntimizeWebModule,
    EventsRoutingModule,
    SharedModule
  ],
  declarations: [
    EventsHomeComponent
  ],
  entryComponents: [
    EventsDetailComponent
  ]
})
export class EventsModule { }