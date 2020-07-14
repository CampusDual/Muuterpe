import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BandsHomeComponent } from './bands-home/bands-home.component';
import { SharedModule } from 'app/shared/shared.module';

const routes: Routes = [{
  path: '',
  component: BandsHomeComponent
},
{
  path:':band_id',
  component:SharedModule
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BandsRoutingModule {}
