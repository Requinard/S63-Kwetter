import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpModule } from '@angular/http';

import { AppComponent } from './app.component';
import { ProfileComponent } from './profile/profile.component';
import { SearchComponent } from './search/search.component';
import {routing} from "./app.routing";
import {TweetService} from "./tweet.service";
import {SearchService} from "./search.service";
import {AccountService} from "./account.service";

@NgModule({
  declarations: [
    AppComponent,
    ProfileComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    routing
  ],
  providers: [TweetService, SearchService, AccountService],
  bootstrap: [AppComponent]
})
export class AppModule { }
