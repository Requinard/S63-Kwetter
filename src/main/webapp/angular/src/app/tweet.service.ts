import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {Tweet} from "./tweet";
import 'rxjs/add/operator/map';
import {API_URL} from "./constants";

@Injectable()
export class TweetService {


  constructor(private http:Http) {

  }
  public query():Observable<Tweet[]>{
    return this.http.get(`${API_URL}/tweets`)
      .map(response => response.json());
  }
}
