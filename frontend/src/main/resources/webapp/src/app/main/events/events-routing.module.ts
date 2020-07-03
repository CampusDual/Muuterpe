import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { EventsHomeComponent } from './events-home/events-home.component';


const routes: Routes = [{
  path:'',
  component: EventsHomeComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class EventsRoutingModule { }
