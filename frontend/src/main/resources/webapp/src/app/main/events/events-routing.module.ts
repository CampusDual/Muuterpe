import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventsHomeComponent } from './events-home/events-home.component';
import { EventsDetailComponent } from './events-detail/events-detail.component';

const routes: Routes = [{
  path:'',
  component: EventsHomeComponent
},
{
  path:'/EVENT_ID',
  component: EventsDetailComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventsRoutingModule { }