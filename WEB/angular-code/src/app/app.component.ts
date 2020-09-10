import { Component, OnInit } from '@angular/core';
import { SharedService } from './shared/shared.service';
import { LoggedUserService } from './shared/services/logged-user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{

  message: string="Autenticando...";
  public shared: SharedService;

  constructor(private loggedUserService: LoggedUserService) { 
    this.shared = SharedService.getInstance();
  }

  ngOnInit() {
    this.loggedUserService.getLoggedUser().subscribe(value => {
      SharedService.getInstance().loggedUser = value.dados;
      this.message="";
    },
      (ex: any) => {
        this.message="Redirecionando...";
        window.location.href = ex.error;
       }
    );
  }
}
