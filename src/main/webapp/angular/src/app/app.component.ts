import { Component } from '@angular/core';
import {TweetService} from "./tweet.service";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  loggedIn=1;
  title = 'app works!';
  userVar = 'userVar works';
  hero: Hero = {
    id: 1,
    name: 'Windstorm'
  };

  constructor(private tweetService:TweetService) {
  }
}
class Hero {
  id: number;
  name: string;
}
