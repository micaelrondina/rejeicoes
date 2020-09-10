import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'formatTimeStr' })
export class formatTimeStr implements PipeTransform {

    transform(value: string): string {
        if (value == null || value.trim().length == 0){
            return value;
        }

        //entrada HHmmss
        //saida HH:mm:ss
        let newStr: string = "";
        
        newStr = value.substring(0,2); //dia
        newStr += ":" + value.substring(2,4); //mes
        newStr += ":" + value.substring(4,6); //ano

        return newStr;
    }

}