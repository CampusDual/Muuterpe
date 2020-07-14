import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventsHomeComponent } from './events-home/events-home.component';
import { SharedModule } from 'app/shared/shared.module';

const routes: Routes = [{
  path:'',
  component: EventsHomeComponent
},
{
  path:':event_id',
  component: SharedModule
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventsRoutingModule { }