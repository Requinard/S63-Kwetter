import {Component, OnDestroy, OnInit} from '@angular/core';
import {AccountService} from "../account.service";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit,OnDestroy {

  account:Account = null;
  userId: string;
  private sub: any;

  constructor(private route: ActivatedRoute, private accountService:AccountService) {
  }

  ngOnInit() {
    this.sub = this.route.params.subscribe(params => {
      this.userId = params['username'];
      this.account = this.accountService.findByUsername(this.userId);
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

}
