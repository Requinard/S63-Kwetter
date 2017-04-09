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

  postTweet(userId: number, content: string) :Observable<Tweet>{
    let data = new URLSearchParams();
    data.append('content', content);
    data.append('userId', userId+"");
    return this.http.post(`${API_URL}/tweets`,data).map(response => response.json());
  }

  toggleHeart(tweetId: number,userId:number) {
    this.http.get(`${API_URL}/tweets/heart/${tweetId}?userId=${userId}`)
      .subscribe();
  }
  getTweetsByUserId(id: number) :Observable<Tweet[]>{
    return this.http.get(`${API_URL}/accounts/tweets/${id}`)
      .map(response => response.json());
  }
}
