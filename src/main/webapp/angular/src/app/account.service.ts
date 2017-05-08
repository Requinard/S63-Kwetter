import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {API_URL} from "./constants";
import {Account} from "./account";

@Injectable()
export class AccountService {
  constructor(private http:Http) {
  }

  public findByUsername(username:string):Observable<Account>{
    return this.http.get(`${API_URL}/accounts/username/${username}`)
      .map(response => response.json());
  }

  search(query: string) :Observable<Account[]>{
    return this.http.get(`${API_URL}/accounts/search/${query}`)
      .map(response => response.json());
  }

  getFollowers(id: number):Observable<Account[]> {
    return this.http.get(`${API_URL}/accounts/followers/${id}`)
      .map(response => response.json());
  }

  follow(loggedIn: number, id: number) {
    return this.http.get(`${API_URL}/accounts/follow/${id}?loggedInUser=${loggedIn}`).subscribe();
  }
}
