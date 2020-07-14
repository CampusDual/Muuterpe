import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from "ontimize-web-ngx";
import { BandsRoutingModule } from './bands-routing.module';
import { BandsHomeComponent } from './bands-home/bands-home.component';
import { SharedModule } from 'app/shared/shared.module';
import { BandsDetailComponent } from 'app/shared/bands-detail/bands-detail.component';


@NgModule({
  imports: [
    CommonModule,
    OntimizeWebModule,
    BandsRoutingModule,
    SharedModule
  ],
  declarations: [
    BandsHomeComponent
  ],
  entryComponents: [
    BandsDetailComponent
  ]
  
})
export class BandsModule {}
