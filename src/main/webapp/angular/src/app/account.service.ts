import { Injectable } from '@angular/core';
import {Http} from "@angular/http";
import {Observable} from "rxjs";
import {API_URL} from "./constants";

@Injectable()
export class AccountService {
  constructor(private http:Http) {
  }
  // public findByUsername(username:string):Observable<Account>{
  public findByUsername(username:string):Account{



    let url = `${API_URL}/accounts/username/${username}`;
    console.log(url);
    let json = this.http.get(url);
    console.log(json);
    let mapped = json.map(response => response.json());
    console.log(mapped);

    return mapped;

  }

}
