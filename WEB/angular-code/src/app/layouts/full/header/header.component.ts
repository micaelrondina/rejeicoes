import { Component } from '@angular/core';
import { LoggedUser } from '../../../shared/models/logged-user';
import { SharedService } from '../../../shared/shared.service';
import { Subscription } from 'rxjs';
import { LogoffService } from './../../../shared/services/logoff.service';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})
export class AppHeaderComponent {
    private sub: Subscription;

    constructor(private logoffService: LogoffService) { }

    getLoginName(): LoggedUser {
        return SharedService.getInstance().loggedUser;
    }

    logoff() {
        this.sub = this.logoffService.logoof().subscribe(v => {
            //redicionando para a page de login
            window.location.href = v.dados; //dados eh onde vem a url da page login
        });
    }

    ngOnDestroy() {
        this.sub.unsubscribe();
    }
}