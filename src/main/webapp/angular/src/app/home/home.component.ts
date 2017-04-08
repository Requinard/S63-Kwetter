import { Component, OnInit } from '@angular/core';
import {Tweet} from "../tweet";
import {TweetService} from "../tweet.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  postContent:string;
  userId:number;
  tweets:Tweet[];

  constructor(private tweetService :TweetService) {
    this.userId = 1;
  }

  ngOnInit() {
    this.getPersonalTweets();
  }

  public post(){
    this.tweetService.postTweet(this.userId,this.postContent);
  }
  public getPersonalTweets  (){
    this.tweetService.personalTweets(this.userId)
      .subscribe(tweets => {
        this.tweets= tweets;
      });
  }
  public toggleHeart(tweetId:number){
    this.tweetService.toggleHeart(tweetId, this.userId);
  }
}
