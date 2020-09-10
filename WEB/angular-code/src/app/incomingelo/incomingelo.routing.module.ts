import { ConsultarArqRetPaywareComponent } from './consultar/retorno-arq-payware/retorno.arq.payware.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ConsultarComponent } from './consultar/consultar.component';
import { BatimentoComponent } from './batimento/batimento.component';
import { ConsultarSinteticoComponent } from './consultar/sintetico/consultar.sintetico.component';
import { EnvioRejeicoesComponent } from './envio-rejeicoes/envio.rejeicoes.component';
import { HomeComponent } from '../shared/home/home.component';
import { AuthGuard } from '../shared/auth.guard';
import { SemAcessoComponent } from '../shared/sem-acesso/sem-acesso.component';

const routes: Routes = [
    { path: 'home', component: HomeComponent},
    { path: 'sem-acesso', component: SemAcessoComponent},
    { path: 'consultar', component: ConsultarComponent, canActivate: [AuthGuard], data: {
        accessRole: 'dxc.incoming.elo.cons' //permissao que essa rota precisa para ser acessada
    }},
    { path: 'consultar-sintetico', component: ConsultarSinteticoComponent, canActivate: [AuthGuard], data: {
        accessRole: 'dxc.incoming.elo.cons' //permissao que essa rota precisa para ser acessada
    }},    
    { path: 'envio-rejeicoes', component: EnvioRejeicoesComponent, canActivate: [AuthGuard], data: {
        accessRole: 'dxc.incoming.elo.envrej' //permissao que essa rota precisa para ser acessada
    }},
    { path: 'batimento-rejeitado', component: BatimentoComponent, canActivate: [AuthGuard], data: {
        accessRole: 'dxc.incoming.elo.batment', //permissao que essa rota precisa para ser acessada
        isModoRejeitado: true
    }},
    { path: 'batimento-acatado', component: BatimentoComponent, canActivate: [AuthGuard], data: {
        accessRole: 'dxc.incoming.elo.batment', //permissao que essa rota precisa para ser acessada
        isModoRejeitado: false
    }},
    { path: 'consultar-ret-payware', component: ConsultarArqRetPaywareComponent, canActivate: [AuthGuard], data: {
        accessRole: 'dxc.incoming.elo.cons' //permissao que essa rota precisa para ser acessada
    }},    
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class IncomingELORoutingModule {}