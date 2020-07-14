import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './home.component';
import { HomeeventsDetailComponent } from './homeevents-detail/homeevents-detail.component';

const routes: Routes = [
  {
    path: '',
    component: HomeComponent
  },
  {
    path:'/EVENT_ID',
    component: HomeeventsDetailComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class HomeRoutingModule { }
