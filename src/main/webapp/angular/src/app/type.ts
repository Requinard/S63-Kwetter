import {Account} from "./account";
export class Type {
  constructor(public Id:number,
              public groupName:string,
              public accounts?:Account[]
  ){

  }

}
