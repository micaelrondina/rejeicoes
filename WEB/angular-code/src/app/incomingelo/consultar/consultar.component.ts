import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { MatSnackBar, MatTableDataSource, MatDialog, MatSort, Sort } from '@angular/material';
import { IncomingService } from '../services/incoming.service';
import { Incoming } from '../../shared/models/incoming';
import { ExcelService } from '../../shared/services/excel.service';
import { ModalConsultarMaisInfoComponent } from './modal-consultar-mais-info/modal.consultar.mais.info.component';
import Utils from '../utils';

@Component({
    selector: 'app-consultar',
    templateUrl: './consultar.component.html',
    styleUrls: ['./consultar.component.css']
})
export class ConsultarComponent implements OnInit, OnDestroy {
    private sub: Subscription[] = [];
    listIncoming: Incoming[] = null;
    qtdTotal: number;
    vlrTotal: number;
    nomeFiltro:string;
    totalRejeitadas:number;
    qtdLiq: number;
    valorLiq: number;
    totalRejeicao: number;
    qtdFiltro: number;
    vlrFiltro: number;
    totalPendentes: number;
    totalReenviadas: number;
    totalAcatadas: number;
    displayedColumns: string[] = [
        'CODIGO_EC', 'NOME_EC', 'CODIGO_PV', 'NOME_PV','CIDADE_PV', 'DATA_MOV', 'VALOR_VENDA', 'PARCELAS', 
        'QTD_DIAS_LIQ_FINAN_TRANS', 'NUM_REF_TRANS', 'COD_AUTH_TRANS', 'QTD_VZ_TRANS_REJ', 'COD_ERRO', 'DESC_MSG_ERRO', 'NUM_LOTE',
        'LOGIN_LIBERACAO', 'DATA_LIBERACAO', 'LOGIN_ACATADO', 'DATA_ACATADO', 'LOGIN_REJEICAO', 'DATA_REJEICAO'
    ];
    dataSource: any = null;//criando dataSource: utilizado para realizar o filtro
    @ViewChild(MatSort) tableSort: MatSort;

    //filtro Transações
    opcFiltroSelect: string = 'T';
    disabledFiltro: boolean = true; //variavel que controla se o Filtro (Todas, Rejeitadas, Liberadas...) esta Habilitado
    exibirBtnExport: boolean = false; //variavel que controla a exibição do botao Exportar
    objectPesquisa: any[];

    constructor(
        private snackBar: MatSnackBar,
        private incomingService: IncomingService,
        private excelService: ExcelService,
        public dialog: MatDialog
    ) { }

    ngOnInit() {}

    //cria o filtro por status da transação no DataSource
    criarFiltroTabela() {
        this.dataSource = new MatTableDataSource(this.listIncoming);
        //pega as definicoes de filtro que estao na classe utils
        this.dataSource.filterPredicate = Utils.filterStatusTransacoes;
    }

    sortData(sort: Sort)  {
        // this.listIncoming = Utils.ordenarLista(this.dataSource, sort);
        this.listIncoming = Utils.ordenarLista(this.listIncoming, sort);
        this.criarFiltroTabela();

        //filtrando de acordo com o Status da Transação
        this.dataSource.filter = this.opcFiltroSelect;
    }

    ngOnDestroy() {
        //fazendo o unsubscribe de todos os subscription feitos
        this.sub.forEach(s => s.unsubscribe());
    }
    
    receberObjetoFiltro(objeto: any): void {
        this.resetPesquisa();
        this.objectPesquisa = objeto;
        this.sub.push(this.incomingService.getListIncoming(objeto).subscribe(v => {
            if (v.dados != null && v.dados.listIncoming != null && v.dados.listIncoming.length != 0){
                this.listIncoming = v.dados.listIncoming;
                this.criarFiltroTabela();
                this.qtdTotal = Number(v.dados.qtd);
                this.vlrTotal = Number(v.dados.valorTotal);
                this.totalRejeicao = this.vlrTotal;

                this.disabledFiltro = false;
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

    resetPesquisa(){
        this.listIncoming = null;
        this.dataSource = null; //como a grid esta vinculada ao datasouce, tenho que limpar ele para o conteudo da grid sumir
        this.qtdTotal = null;
        this.vlrTotal = null;
        this.disabledFiltro = true;
        this.exibirBtnExport = false;
        this.opcFiltroSelect = 'T';
        this.totalRejeitadas = 0;
        this.totalAcatadas = 0;
        this.totalRejeitadas = 0;
        this.totalRejeicao = 0;
        this.qtdFiltro = 0;
        this.vlrFiltro = 0;
        this.nomeFiltro = null;
        this.totalPendentes=0;
        Utils.removerOrdenacaoTable(this.tableSort);
    }



    changeOpcFiltroTransacao(opcFiltro: string) {
        this.opcFiltroSelect = opcFiltro;//recebe a letra do filtro escolhido (T, L, A, R)
        
        //filtrando
        this.dataSource.filter = this.opcFiltroSelect;

        if (this.dataSource.filter === "T"){
            //atualizando: QTD e VALOR total
            this.qtdTotal = this.dataSource.filteredData.length
            this.vlrTotal = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0); //somando valor
            this.totalRejeicao = this.vlrTotal;
    }
    
    if (this.opcFiltroSelect === "R"){
        this.nomeFiltro = "Rejeitadas";
        this.totalRejeitadas = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0);
        this.valorLiq = this.totalRejeicao -  this.totalRejeitadas;
        this.qtdLiq = this.qtdTotal - this.dataSource.filteredData.length;
    }
    else if (this.opcFiltroSelect === "A"){
        this.nomeFiltro = "Acatadas";
        this.totalAcatadas = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0);
        this.valorLiq = this.totalRejeicao -  this.totalAcatadas;
        this.qtdLiq = this.qtdTotal - this.dataSource.filteredData.length;
    } 
    else if (this.opcFiltroSelect === "L"){
        this.nomeFiltro = "Reenviadas";
        this.totalReenviadas = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0);
        this.valorLiq = this.totalRejeicao -  this.totalReenviadas;
        this.qtdLiq = this.qtdTotal - this.dataSource.filteredData.length;
    }
    else if (this.opcFiltroSelect === "P"){
        this.nomeFiltro = "Pendentes";
        this.totalPendentes = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0);
        this.valorLiq = this.totalRejeicao -  this.totalPendentes;
        this.qtdLiq = this.qtdTotal - this.dataSource.filteredData.length;
    }
    else {
        this.totalRejeicao = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0);
        this.valorLiq = this.totalRejeicao;
        this.qtdLiq = this.qtdTotal - this.dataSource.filteredData.length;
    }
        //atualizando: QTD e VALOR total
        this.qtdFiltro = this.dataSource.filteredData.length
        this.vlrFiltro = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0); //somando valor
    }

    exportarGridExcel():void {
        //ajustando a lista para exportar
        if (this.dataSource.filteredData == null || this.dataSource.filteredData.length == 0){
            this.openSnackBar("Nenhum dado encontrado com esse filtro", "");
        } else {
            this.excelService.exporExcelConsultaAnalitica(this.dataSource.filteredData, this.objectPesquisa, "Consulta Analítica", "Consulta_Analitica");
        }
    }

    consultarMaisInformacoes(idMatriz: any, idItem: any, numRefTrans: any, nrParcela: any, idTpTrans: any){
        this.openDialog({idMatriz: idMatriz, idItem: idItem, numRefTrans: numRefTrans, nrParcela: nrParcela, idTpTrans: idTpTrans});
    }

    openDialog(dados: any): void {
        this.dialog.open(ModalConsultarMaisInfoComponent, {
            width: "90%",
            height: "95%",
            data: dados
        });
    }
}