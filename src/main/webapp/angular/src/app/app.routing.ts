import {RouterModule, Routes} from '@angular/router';
import {ModuleWithProviders} from '@angular/core';
import {SearchComponent} from "./search/search.component";
import {AppComponent} from "./app.component";
import {ProfileComponent} from "./profile/profile.component";
import {HomeComponent} from "./home/home.component";

// component: AppComponent,

const appRoutes: Routes = [
  {
    component: HomeComponent,
    path: '',
  },
  {
    component: SearchComponent,
    path: 'search/:query'
  },
  {
    component: ProfileComponent,
    path: 'profile/:username'
  }
];

export const routing: ModuleWithProviders = RouterModule.forRoot(appRoutes);
