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
    xed
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
    for (let i = 0; i < this.followers.length; i++) {
      let account = this.followers[i]
      if (account.id == this.loggedIn) {
        return true;
      }
    }
    return false;
  }

  public follow() {
    if(this.isFollowing()){
      console.log('isFollowing');
      let index = this.followers.findIndex(x => x.id == this.loggedIn);
      this.followers.splice(index, 1);
    }else{
      console.log('!isFollowing');
      //TODO : get the loggedInUserName
      let account:Account = <Account>{id : this.loggedIn,userName:this.userId};
      this.followers.push(account);
    }
    this.accountService.follow(this.loggedIn, this.account.id);
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
