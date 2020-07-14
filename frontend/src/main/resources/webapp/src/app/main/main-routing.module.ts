import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuardService } from 'ontimize-web-ngx';

import { MainComponent } from './main.component';
import { HomeModule } from './home/home.module';
import { BandsModule } from './bands/bands.module';
import { EventsModule } from './events/events.module';

export function loadHomeModule() {
  return HomeModule;
}

export function loadBandsModule() {
  return BandsModule;
}

export function loadEventsModule() {
  return EventsModule;
}

export const routes: Routes = [
  {
    path: '',
    component: MainComponent,
    canActivate: [AuthGuardService],
    children: [
      { path: '', redirectTo: 'home', pathMatch: 'full' },
      {
        path: 'home',
        loadChildren: loadHomeModule
      },
      {
        path: 'bands',
        loadChildren: loadBandsModule
      },
      {
        path: 'events',
        loadChildren: loadEventsModule
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
