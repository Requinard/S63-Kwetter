import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {SearchComponent} from "./search/search.component";
import {AppComponent} from "./app.component";
import {ProfileComponent} from "./profile/profile.component";

const appRoutes: Routes = [
  {
    component: AppComponent,
    path: '',
  },
  {
    component: SearchComponent,
    path: 'search'
  },
  {
    component: ProfileComponent,
    path: 'profile/:username'
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
