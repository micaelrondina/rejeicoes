import { take } from 'rxjs/operators';


import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { Parametro } from './../models/parametro';
import { ApiResponse } from './../models/response/api-response';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({
    providedIn: 'root'
})
export class ParametroService {

    private readonly API = `${environment.API}core/parametros`;

    constructor(private http: HttpClient) { }

    getCodTransacoes(): Observable<ApiResponse<Parametro>> {
        return this.http.get<ApiResponse<Parametro>>(`${this.API}/codtransacoes`, httpOptions).pipe(take(1));
    }

    getCredeciadores(): Observable<ApiResponse<Parametro>> {
        return this.http.get<ApiResponse<Parametro>>(`${this.API}/credenciadores`, httpOptions).pipe(take(1));
    }
}
