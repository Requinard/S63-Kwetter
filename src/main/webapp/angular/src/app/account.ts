import {Type} from "./type";
import {Tweet} from "./tweet";
export class Account {

  constructor(public id:number,
              public userName: string,
              public email:string,
              public bio:string,
              public firstName:string,
              public lastName:string,
              public groups?:Type[],
              public tweets?:Tweet[],
              public following?:Account[],
              public mentions?:Tweet[]
  ) {}
}
