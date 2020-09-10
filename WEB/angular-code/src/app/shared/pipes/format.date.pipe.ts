import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'formatDateStr' })
export class formatDateStr implements PipeTransform {

    transform(value: string): string {
        if (value == null || value.trim().length == 0){
            return value;
        }
        
        //entrada yyyymmdd
        //saida dd/mm/aaaaa
        let newStr: string = "";
        
        newStr = value.substring(6,8); //dia
        newStr += "/" + value.substring(4,6); //mes
        newStr += "/" + value.substring(0,4); //ano

        return newStr;
    }

}