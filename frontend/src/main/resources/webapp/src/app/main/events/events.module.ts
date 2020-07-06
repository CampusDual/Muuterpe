import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from "ontimize-web-ngx";
import { EventsRoutingModule } from './events-routing.module';
import { EventsHomeComponent } from './events-home/events-home.component';


@NgModule({
  imports: [
    CommonModule,
    OntimizeWebModule,
    EventsRoutingModule
  ],
  declarations: [
    EventsHomeComponent
  ]
})
export class EventsModule { }
