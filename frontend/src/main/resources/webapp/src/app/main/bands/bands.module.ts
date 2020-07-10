import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OntimizeWebModule } from "ontimize-web-ngx";
import { BandsRoutingModule } from './bands-routing.module';
import { BandsHomeComponent } from './bands-home/bands-home.component';
import { BandsDetailComponent } from './bands-detail/bands-detail.component';
import { SafePipe } from 'app/safe.pipe';



@NgModule({
  imports: [
    CommonModule,
    OntimizeWebModule,
    BandsRoutingModule
  ],
  declarations: [
    BandsHomeComponent,
    BandsDetailComponent,
    SafePipe
  ]
})
export class BandsModule {}
