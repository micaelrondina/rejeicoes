import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material';
import { SinteticoService } from '../../services/sintetico.service';
import { ExcelService } from '../../../shared/services/excel.service';
import Utils from '../../utils';

@Component({
    selector: 'app-consultar-sintetico',
    templateUrl: './consultar.sintetico.component.html',
    styleUrls: ['./consultar.sintetico.component.css']
})
export class ConsultarSinteticoComponent implements OnInit, OnDestroy {
    listSintetico: any = null;
    exibirBtnExport: boolean = false; //variavel que controla a exibição do botao Exportar
    displayedColumns: string[] = [
        'DATA_OUTGOING', 'NOME_ARQ_OUTGOING', 'QTD_OUTGOING', 'VLR_TOTAL_OUTGOING',
        'DATA_RET_INCOMING_PARC', 'QTD_INCOMING_PARC', 'VLR_INCOMING_PARC',
        'DATA_RET_INCOMING_TOT', 'QTD_INCOMING_TOT', 'VLR_INCOMING_TOT',
        'QTD_LIQUIDO', 'VLR_LIQUIDO'
    ];

    private sub: Subscription[] = [];
    objectPesquisa: any[];

    constructor(
        private snackBar: MatSnackBar,
        private sinteticoService: SinteticoService,
        private excelService: ExcelService
    ) { }

    ngOnInit() { }

    ngOnDestroy() {
        //fazendo o unsubscribe de todos os subscription feitos
        this.sub.forEach(s => s.unsubscribe());
    }
    
    receberObjetoFiltro(objeto: any): void {
        this.resetPesquisa(true);
        this.objectPesquisa = objeto;
        this.sub.push(this.sinteticoService.getListSintetico(objeto).subscribe(v => {
            if (v.dados != null && v.dados.length != 0){
                this.listSintetico = v.dados;
                this.exibirBtnExport = true;
            } else {
                this.openSnackBar("Nenhum dado encontrado com esse filtro", "");
            }
        },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
        ));
    }

    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
          duration: 4000,
        });
    }

    resetPesquisa(trocouValor: boolean){
        this.listSintetico = null;
        this.exibirBtnExport = false;
    }

    formataValor(valor: number) {
        return valor != null ? valor.toLocaleString('pt-br', {minimumFractionDigits: 2}) : '';
    }

    mostraQuantidade(dt: string, qtd: number) {
        return dt != null ? qtd : '';
    }

    exportarGridExcel():void {
        //ajustando a lista para exportar
        if (this.listSintetico == null || this.listSintetico.length == 0){
            this.openSnackBar("Nenhum dado encontrado com esse filtro", "");
        } else {
            this.excelService.exporExcelConsultaSintetica(this.listSintetico, this.objectPesquisa);
        }
    }

    checkRejTotal(valor: any, rejTotal: number) {
        if (rejTotal == 1){ //Rejeitado Total
            return "Rejeição Total";
        } else {
            return valor;
        }
    }
}
