import { NgModule, NgModuleFactory } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MainModule } from './main/main.module';


export function loadMainModule() {
  return MainModule;
}

export const routes: Routes = [
  { path: 'main', loadChildren: loadMainModule },
  { path: '', redirectTo: 'main', pathMatch: 'full' },

];

const opt = {
  enableTracing: false
  // true if you want to print navigation routes
};

@NgModule({
  imports: [RouterModule.forRoot(routes, opt)],
  exports: [RouterModule],
  providers: []
})
export class AppRoutingModule { }
