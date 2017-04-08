import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {Tweet} from "./tweet";
import 'rxjs/add/operator/map';
import {API_URL} from "./constants";
import { URLSearchParams } from "@angular/http"

@Injectable()
export class TweetService {


  constructor(private http:Http) {

  }
  personalTweets(userId: number):Observable<Tweet[]> {
    return this.http.get(`${API_URL}/tweets?id=${userId}`)
      .map(response => response.json());
  }

  postTweet(userId: number, content: string) {
    let data = new URLSearchParams();
    data.append('content', content);
    data.append('userId', userId+"");
    this.http.post(`${API_URL}/tweets`,data);
  }

  toggleHeart(tweetId: number,userId:number) {
    this.http.get(`${API_URL}/heart/${tweetId}?userId=${userId}`);
  }
}
