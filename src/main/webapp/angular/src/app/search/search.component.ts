import { Component, OnInit } from '@angular/core';
import {TweetService} from "../tweet.service";

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css'],
  providers:[TweetService]

})
export class SearchComponent implements OnInit {

  // searchVar = 'a';
  constructor() { }

  ngOnInit() {
    // this.searchVar = 'b';
  }
}

