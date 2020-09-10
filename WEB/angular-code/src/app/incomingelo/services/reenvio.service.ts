import { take } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../../shared/models/response/api-response';
import { ApiRequest } from '../../shared/models/request/api-request';
import { Reenvio } from '../../shared/models/reenvio';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({
    providedIn: 'root'
})
export class ReenvioService {

    private readonly API = `${environment.API}core/reenvio`;

    constructor(private http: HttpClient) { }

    getRespostaEnvio(dados: ApiRequest<Reenvio>): Observable<ApiResponse<string>>  {
        return this.http.post<ApiResponse<string>>(`${this.API}`, JSON.stringify(dados), httpOptions).pipe(take(1));
    }
}