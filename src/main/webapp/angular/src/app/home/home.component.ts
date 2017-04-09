import {Component, OnInit} from '@angular/core';
import {Tweet} from "../tweet";
import {Account} from "../account";
import {TweetService} from "../tweet.service";
import {AccountService} from "../account.service";
import {forEach} from "@angular/router/src/utils/collection";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  postContent: string;
  userName: string;
  account: Account;
  tweets: Tweet[];
  loggedIn: number;

  constructor(private tweetService: TweetService, private accountService: AccountService) {
    this.userName = "wouterv";
    this.loggedIn = 1;
  }

  ngOnInit() {
    this.getCurrentAccount();
  }

  public getCurrentAccount() {
    this.accountService.findByUsername(this.userName)
      .subscribe(account => {
        this.account = account;
        this.getTweetsByCurrentAccount();
        this.getPersonalTweets();
      });
  }

  public getTweetsByCurrentAccount() {
    this.tweetService.getTweetsByUserId(this.account.id)
      .subscribe(tweets => this.account.tweets = tweets);
  }

  public hasHearted(tweet: Tweet): boolean {

    if (tweet.hearted == null) {
      return false;
    }
    for (let i = 0; i < tweet.hearted.length; i++) {
      let account = tweet.hearted[i]
      if (account.id == this.loggedIn) {
        return true;
      }
    }
    return false;
  }

  public post() {
    this.tweetService.postTweet(this.account.id, this.postContent)
      .subscribe(tweet => {
        // this.tweets.push(tweet);
        this.tweets.splice(0,0,tweet);
      });
  }


  public getPersonalTweets() {
    this.tweetService.personalTweets(this.account.id)
      .subscribe(tweets => {
        this.tweets = tweets;
      });
  }

  public toggleHeart(tweet: Tweet) {
    if(this.hasHearted(tweet)){
      let index = tweet.hearted.findIndex(x => x.id == this.loggedIn);
      tweet.hearted.splice(index, 1);
    }else{
      let account:Account = <Account>{id : this.loggedIn};
      tweet.hearted.push(account);
    }
    this.tweetService.toggleHeart(tweet.id, this.account.id);
  }
}
