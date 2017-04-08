import {Tweet} from "./tweet";
export class HashTag {
    constructor(public Id:number,
                public name:string,
                public tweets?:Tweet[]
    ){}
}
