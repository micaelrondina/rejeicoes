import { take } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../../shared/models/response/api-response';
import { ApiRequest } from '../../shared/models/request/api-request';
import { Batimento } from '../../shared/models/batimento';
import { Incoming } from '../../shared/models/incoming';
import { ReturnProcBatAcatado } from '../../shared/models/return-proc-bat-acatado';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({
    providedIn: 'root'
})
export class BatimentoService {

    private readonly API = `${environment.API}core/batimento`;

    constructor(private http: HttpClient) { }

    getListBatimento(filtro: ApiRequest<Batimento>): Observable<ApiResponse<Incoming[]>> {
        return this.http.post<ApiResponse<Incoming[]>>(`${this.API}`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }

    processarBatimentoAcatado(): Observable<ApiResponse<ReturnProcBatAcatado>> {
        return this.http.patch<ApiResponse<ReturnProcBatAcatado>>(`${this.API}/processar-acatado`, null, httpOptions).pipe(take(1));
    }

    processarBatimentoRejeitado(filtro: ApiRequest<Batimento>): Observable<ApiResponse<string>> {
        return this.http.patch<ApiResponse<string>>(`${this.API}/processar-rejeitado`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }

    getListBatimentoById(filtro: ApiRequest<string>): Observable<ApiResponse<Incoming[]>> {
        return this.http.post<ApiResponse<Incoming[]>>(`${this.API}/get-by-ids`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }
}
