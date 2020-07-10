import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BandsHomeComponent } from './bands-home/bands-home.component';
import { BandsDetailComponent } from './bands-detail/bands-detail.component';


const routes: Routes = [{
  path: '',
  component: BandsHomeComponent
},
{
  path:':band_id',
  component: BandsDetailComponent
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BandsRoutingModule {}
