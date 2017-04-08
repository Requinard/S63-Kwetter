import {Component, OnInit} from '@angular/core';
import {TweetService} from "../tweet.service";
import {ActivatedRoute} from "@angular/router";
import {AccountService} from "../account.service";
import {Account} from "../account";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  providers: [TweetService]

})
export class SearchComponent implements OnInit {

  accounts: Account[];

  constructor(private route: ActivatedRoute,private accountService: AccountService) {
  }

  ngOnInit() {
    this.route.params.map(params => params['query'])
      .subscribe(query => this.getBySearchParam(query));
  }

  private getBySearchParam(query:string){
    this.accountService.search(query)
      .subscribe(accounts => this.accounts = accounts);
  }
}

