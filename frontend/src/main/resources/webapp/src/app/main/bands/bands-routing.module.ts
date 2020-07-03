import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { BandsHomeComponent } from './bands-home/bands-home.component';


const routes: Routes = [{
  path: '',
  component: BandsHomeComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BandsRoutingModule {}
