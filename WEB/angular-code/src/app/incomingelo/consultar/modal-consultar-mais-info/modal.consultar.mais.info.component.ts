import { Component, OnInit, OnDestroy, Inject, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { Subscription } from 'rxjs';
import { IncomingService } from '../../services/incoming.service';
import { MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { ApiRequest } from '../../../shared/models/request/api-request';
import { FiltroConsultarMaisInfo } from '../../../shared/models/filtro-consultar-mais-info';
import Utils from '../../utils';

@Component({
    selector: 'modal-consultar-mais-info',
    templateUrl: './modal.consultar.mais.info.component.html',
    styleUrls: ['./modal.consultar.mais.info.component.css']
})
export class ModalConsultarMaisInfoComponent implements OnInit, OnDestroy {
    private sub: Subscription[] = [];

    //controla se ja carregou o conteudo da aba Mais Informações (a aba Historico carrega automaticamente)
    carregouAbaMaisInformacoes: boolean = false;
    selectedTab: number = 0; //aba Historico eh a padrao

    //ABA HISTORICO
    displayedColumnsHist: string[] = [
        'CUPON', 'CUOTAS_VAN', 'TRAN_CODE', 'PRODUCTO', 'FECHA_OUTGOING', 'IMPORTE', 'MARCA', 'ACQUIRER_REF_NUM'
    ];
    listHistorico: any[] = null;
    maisInformacoes: any;

    //
    temDadosHist: boolean = false;
    mensagemHist: string = "Aguarde, carregando dados..."
    temDadosMaisInfo: boolean = false;
    mensagemMaisInfo: string = "Aguarde, carregando dados..."    

    constructor(
        public dialogRef: MatDialogRef<ModalConsultarMaisInfoComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
        private incomingService: IncomingService,
        private snackBar: MatSnackBar,
      ) { } 

    ngOnInit() {
        //Em Tempo de Projeto, ocorre esse ERRO no Console:
        //ERROR Error: ExpressionChangedAfterItHasBeenCheckedError: Expression has changed after it was checked.
        //por enquanto estamos desconsiderando, pois apos "ng build --prod", esse erro nao ocorre mais e nao impacta em nada no projeto.
        if (this.data == null || this.data.numRefTrans == null) {
            this.temDadosHist = false;
            this.mensagemHist = "Não há dados a exibir!"
            return;
        }

        //so carrega o Historico se tiver o numRefTrans
        var objectRequest = <ApiRequest<FiltroConsultarMaisInfo>>{
            filtro: this.data
        }
        this.sub.push(this.incomingService.getIncomingHistorico(objectRequest).subscribe(v => {
            if (v.dados != null && v.dados.length != 0){
                this.listHistorico = v.dados
                this.temDadosHist = true;
            } else {
                this.temDadosHist = false;
                this.mensagemHist = "Não há dados a exibir!"
            }
        }));
    }

    ngOnDestroy() {
        //fazendo o unsubscribe de todos os subscription feitos
        this.sub.forEach(s => s.unsubscribe());
    }

    fechar() {
        this.dialogRef.close();
    }

    changeTab(tabIndex: number){
        if (tabIndex === 1 && !this.carregouAbaMaisInformacoes) { //aba Mais Informações e nao carregou as informações dela...
            //entao carrega
            var objectRequest = <ApiRequest<FiltroConsultarMaisInfo>>{
                filtro: this.data
            }
            this.sub.push(this.incomingService.getIncomingMaisInfo(objectRequest).subscribe(v => {
                this.carregouAbaMaisInformacoes = true;
                if (v.dados != null && v.dados.length != 0){
                    this.maisInformacoes = v.dados;
                    this.temDadosMaisInfo = true;
                } else {
                    this.temDadosMaisInfo = false;
                    this.mensagemMaisInfo = "Não há dados a exibir!"
                }
            },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
            ))
        }
    }

    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
          duration: 4000,
        });
    }

    getNumber(valor: any){
        if (valor) {
            return Number(valor);    
        }
        return Number(0);
    }

    getDescricaoErro(descricao: string, descricaoTraduzida: string){
        if (descricao == null) {
            return "";
        }
        return descricao.trim().length != 0 ? descricao : descricaoTraduzida;
    }
}