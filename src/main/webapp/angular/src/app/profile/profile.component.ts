import {Component, OnDestroy, OnInit} from '@angular/core';
import {AccountService} from "../account.service";
import {ActivatedRoute} from "@angular/router";
import {Account} from "../account";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit, OnDestroy {

  account: Account = null;
  userId: string;
  private sub: any;

  constructor(private route: ActivatedRoute, private accountService: AccountService) {
  }

  ngOnInit() {
    this.route.params.map(params => params['username'])
      .subscribe(username => this.getByUsername(username));
  }

  private getByUsername(username: string) {
    this.accountService.findByUsername(username)
      .subscribe(account => {
        this.account = account
      });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
