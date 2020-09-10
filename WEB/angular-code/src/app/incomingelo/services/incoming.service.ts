import { Batimento } from './../../shared/models/batimento';
import { take } from 'rxjs/operators';

import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../../environments/environment';
import { ApiResponse } from '../../shared/models/response/api-response';
import { ApiRequest } from '../../shared/models/request/api-request';
import { FiltroConsultar } from '../../shared/models/filtro-consultar';
import { IncomingWrapper } from './../../shared/models/incoming.wrapper';
import { FiltroConsultarMaisInfo } from '../../shared/models/filtro-consultar-mais-info';
import { IncomingHist } from '../../shared/models/incoming.hist';

const httpOptions = {
    headers: new HttpHeaders({
        'Content-Type': 'application/json'
    })
};
@Injectable({
    providedIn: 'root'
})
export class IncomingService {

    private readonly API = `${environment.API}core/incoming`;

    constructor(private http: HttpClient) { }

    getListIncoming(filtro: ApiRequest<FiltroConsultar>): Observable<ApiResponse<IncomingWrapper>> {
        return this.http.post<ApiResponse<IncomingWrapper>>(`${this.API}`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }

    getIncomingHistorico(filtro: ApiRequest<FiltroConsultarMaisInfo>): Observable<ApiResponse<IncomingHist[]>> {
        return this.http.post<ApiResponse<IncomingHist[]>>(`${this.API}/historico`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }

    getIncomingMaisInfo(filtro: ApiRequest<FiltroConsultarMaisInfo>): any {
        return this.http.post(`${this.API}/mais-informacoes`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }

    getIncomingRetArqPayware(filtro: ApiRequest<Batimento>): any {
        return this.http.post(`${this.API}/ret-arq-payware`, JSON.stringify(filtro), httpOptions).pipe(take(1));
    }
}
