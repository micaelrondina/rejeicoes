import { take } from 'rxjs/operators';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({
    providedIn: 'root'
})
export class LogoffService {

    private readonly API = `${environment.API}core/logoff`;

    constructor(private http: HttpClient) { }

    logoof(): Observable<any> {
        return this.http.get<any>(`${this.API}`, httpOptions).pipe(take(1));
    }
}