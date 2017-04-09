import {Component, OnDestroy, OnInit} from '@angular/core';
import {AccountService} from "../account.service";
import {ActivatedRoute} from "@angular/router";
import {Account} from "../account";
import {TweetService} from "app/tweet.service";
import {withIdentifier} from "codelyzer/util/astQuery";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  account: Account = null;
  followers: Account[];
  userId: string;
  loggedIn: number;
  private sub: any;

  constructor(private route: ActivatedRoute, private accountService: AccountService, private tweetService: TweetService) {
    this.loggedIn = 1;
  }

  ngOnInit() {
    this.route.params.map(params => params['username'])
      .subscribe(username => this.getByUsername(username));
  }

  private getByUsername(username: string) {
    this.accountService.findByUsername(username)
      .subscribe(account => {
        this.account = account;
        this.getFollowers();
      });
  }

  private getFollowers() {
    this.accountService.getFollowers(this.account.id)
      .subscribe(followers => {
        this.followers = followers;
        this.getTweets();
      });
  }

  private getTweets() {
    this.tweetService.getTweetsByUserId(this.account.id)
      .subscribe(tweets => this.account.tweets = tweets);
  }

  public isFollowing() {
    if (this.followers == null) {
      return false;
    }
    this.followers.forEach((value, index, array) => {
      if (value.id == this.loggedIn) {
        return true;
      }
    });
    return false;
  }

  public follow() {
    this.accountService.follow(this.loggedIn, this.account.id);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
