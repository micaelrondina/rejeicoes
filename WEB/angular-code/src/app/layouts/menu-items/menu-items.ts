import { Injectable } from '@angular/core';
import { SharedService } from '../../shared/shared.service';

export interface Menu {
  state: string;
  name: string;
  type: string;
  icon: string;
  task: string;
}

const MENUITEMS = [
  { state: 'consultar', type: 'link', name: 'Consulta Analítica', icon: 'search', task: 'dxc.incoming.elo.cons' },
  { state: 'consultar-sintetico', type: 'link', name: 'Consulta Sintética', icon: 'search', task: 'dxc.incoming.elo.cons' },
  { state: 'envio-rejeicoes', type: 'link', name: 'Reenvio', icon: 'send', task: 'dxc.incoming.elo.envrej' },
  { state: 'batimento-rejeitado', type: 'link', name: 'Batimento - Rejeitado', icon: 'build', task: 'dxc.incoming.elo.batment' },
  { state: 'batimento-acatado', type: 'link', name: 'Batimento - Acatado', icon: 'build', task: 'dxc.incoming.elo.batment' },
  { state: 'consultar-ret-payware', type: 'link', name: 'Retorno Payware', icon: 'save_alt', task: 'dxc.incoming.elo.cons' },
];

@Injectable()
export class MenuItems {
  getMenuitem(): Menu[] {
    let ss = SharedService.getInstance();
    let listItensMenu = [];

    //verificando para quais itens de menu o usuario tem acesso
    if(ss!=null && ss.loggedUser!=null) {
        MENUITEMS.forEach(element => {
            if (ss.loggedUser.tasks.some(v => v === element.task)){
                listItensMenu.push(element);
            }
        });
        return listItensMenu;
    }else{
      return MENUITEMS;
    }
  }
}
