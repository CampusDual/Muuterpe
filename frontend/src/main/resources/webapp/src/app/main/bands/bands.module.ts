import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from "ontimize-web-ngx";
import { BandsRoutingModule } from './bands-routing.module';
import { BandsHomeComponent } from './bands-home/bands-home.component';


@NgModule({
  imports: [
    CommonModule,
    OntimizeWebModule,
    BandsRoutingModule
  ],
  declarations: [
    BandsHomeComponent
  ]
})
export class BandsModule {}
