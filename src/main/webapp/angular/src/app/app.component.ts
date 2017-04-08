import { Component } from '@angular/core';
import {TweetService} from "./tweet.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  searchVar:string;

  constructor(private tweetService:TweetService) {
  }
}
