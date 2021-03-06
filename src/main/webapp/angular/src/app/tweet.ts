import {Account} from "./account";
import {HashTag} from "./hash-tag";
export class Tweet {
  constructor(public id:number,
              public content: string,
              public username: string,
              public date:Date,
              public postAccount:Account,
              public hearted:Account[],
              public hashtags:HashTag[],
              public mentions:Account[]
  ) {}
}
