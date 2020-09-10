import { take } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../../shared/models/response/api-response';
import { ApiRequest } from '../../shared/models/request/api-request';
import { FiltroConsultar } from '../../shared/models/filtro-consultar';
import { Sintetico } from './../../shared/models/sintetico';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({
    providedIn: 'root'
})
export class SinteticoService {

    private readonly API = `${environment.API}core/sintetico`;

    constructor(private http: HttpClient) { }

    getListSintetico(filtro: ApiRequest<FiltroConsultar>): Observable<ApiResponse<Sintetico[]>>  {
        return this.http.post<ApiResponse<Sintetico[]>>(`${this.API}`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }
}