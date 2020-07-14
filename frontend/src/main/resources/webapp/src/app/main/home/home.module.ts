import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from '../../shared/shared.module';
import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home-routing.module';
import { EventsDetailComponent } from '../../shared/events-detail/events-detail.component';
import { BandsDetailComponent } from 'app/shared/bands-detail/bands-detail.component';


@NgModule({
  imports: [
    SharedModule,
    OntimizeWebModule,
    HomeRoutingModule
  ],
  declarations: [
    HomeComponent

  ],
  entryComponents: [
BandsDetailComponent,
    EventsDetailComponent
  ]
})
export class HomeModule { }
 