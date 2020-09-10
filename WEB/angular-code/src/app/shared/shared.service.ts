import { Injectable, EventEmitter } from '@angular/core';
import { LoggedUser } from './models/logged-user';

@Injectable()
export class SharedService {

    public static instance: SharedService = null;
    loggedUser: LoggedUser;
    showTemplate = new EventEmitter<boolean>();

    //salva as informações do Filtro (usado para exportar para Excel)
    infoAdqProdTpTrans: any;

    constructor() {
        return SharedService.instance = SharedService.instance || this;
    }

    public static getInstance() {
        if (this.instance == null) {
            this.instance = new SharedService();
        }

        return this.instance;
    }

    isLoggedIn(): boolean {
        if (this.loggedUser == null) {
            return false;
        }
        return this.loggedUser.name != '';
    }
}
