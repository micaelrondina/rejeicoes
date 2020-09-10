import { Observable } from 'rxjs/Observable';
import { Injectable } from '@angular/core';
import { Route, Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanLoad } from '@angular/router';
import { SharedService } from './shared.service';

@Injectable()
export class AuthGuard implements CanActivate {

  public shared: SharedService;
  
  constructor(private router: Router) { 
                this.shared = SharedService.getInstance();
  }
  
  canActivate(
      route: ActivatedRouteSnapshot, 
      state: RouterStateSnapshot): Observable<boolean> | boolean {
    
    //permissao necessaria para acessar
    const expectedRole = route.data.accessRole;
    
    //se esta logado e tem a permissao de acesso
    if(this.shared.isLoggedIn() && this.shared.loggedUser.tasks.some(v => v === expectedRole)){
        return true;
    }

    this.router.navigate(["/sem-acesso"]);
    return false;
  }

}
