import 'hammerjs';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';

import { MaterialModule } from '../material-module';
import { CdkTableModule } from '@angular/cdk/table';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FlexLayoutModule } from '@angular/flex-layout';

import { ConsultarComponent } from './consultar/consultar.component';
import { BatimentoComponent } from './batimento/batimento.component';
import { EnvioRejeicoesComponent } from './envio-rejeicoes/envio.rejeicoes.component';
import { IncomingELORoutingModule } from './incomingelo.routing.module';
import { HomeComponent } from '../shared/home/home.component';
import { SemAcessoComponent } from '../shared/sem-acesso/sem-acesso.component';
import { FiltroConsultaComponent } from './filtro-consulta/filtro-consulta.component';
import { ConsultarSinteticoComponent } from './consultar/sintetico/consultar.sintetico.component';

import { ModalConsultarMaisInfoComponent } from './consultar/modal-consultar-mais-info/modal.consultar.mais.info.component';
import { FiltroStatusTransacaoComponent } from './filtro-status-transacao/filtro.status.transacao.component';
import { ConsultarArqRetPaywareComponent } from './consultar/retorno-arq-payware/retorno.arq.payware.component';
import { formatDateStr } from '../shared/pipes/format.date.pipe';
import { formatTimeStr } from '../shared/pipes/format.time.pipe';

@NgModule({
  imports: [
    CommonModule,
    IncomingELORoutingModule,
    MaterialModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    FlexLayoutModule,
    CdkTableModule
  ],
  providers: [],
  entryComponents: [ModalConsultarMaisInfoComponent],
  declarations: [
    ConsultarComponent,
    ModalConsultarMaisInfoComponent,
    BatimentoComponent,
    EnvioRejeicoesComponent,
    ConsultarSinteticoComponent,
    HomeComponent,
    SemAcessoComponent,
    FiltroConsultaComponent,
    FiltroStatusTransacaoComponent,
    ConsultarArqRetPaywareComponent,
    formatDateStr,
    formatTimeStr
  ]
})
export class IncomingELOModule {}
