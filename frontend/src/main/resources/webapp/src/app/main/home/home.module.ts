import { NgModule } from '@angular/core';
import { OntimizeWebModule } from 'ontimize-web-ngx';
import { SharedModule } from '../../shared/shared.module';
import { HomeComponent } from './home.component';
import { HomeRoutingModule } from './home-routing.module';
import { BandsDetailComponent } from '../../shared/bands-detail/bands-detail.component';
import { EventsDetailComponent } from '../../shared/events-detail/events-detail.component';
import { SafePipe } from 'app/safe.pipe';


@NgModule({
  imports: [
    SharedModule,
    OntimizeWebModule,
    HomeRoutingModule
  ],
  declarations: [
    HomeComponent,
    SafePipe,
    BandsDetailComponent,
    EventsDetailComponent
  ],
  entryComponents: [
    BandsDetailComponent,
    EventsDetailComponent
  ]
})
export class HomeModule { }
 