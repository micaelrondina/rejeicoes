import { Component, OnInit, OnDestroy, ViewChild } from '@angular/core';
import { Subscription } from 'rxjs';
import { MatSnackBar, MatCheckbox, MatTableDataSource, MatDialog, Sort, MatSort } from '@angular/material';
import { IncomingService } from '../services/incoming.service';
import { ApiRequest } from '../../shared/models/request/api-request';
import { Reenvio } from '../../shared/models/reenvio';
import { SharedService } from '../../shared/shared.service';
import { ReenvioService } from '../services/reenvio.service';
import { SelectionModel } from '@angular/cdk/collections';
import { ModalConsultarMaisInfoComponent } from '../consultar/modal-consultar-mais-info/modal.consultar.mais.info.component';
import Utils from '../utils';
import { ExcelService } from '../../shared/services/excel.service';

@Component({
    selector: 'app-envio-rejeicoes',
    templateUrl: './envio.rejeicoes.component.html',
    styleUrls: ['./envio.rejeicoes.component.css']


})
export class EnvioRejeicoesComponent implements OnInit, OnDestroy {
    listIncoming: any[] = null;
    //Valores Liquidos
    valorLiq: number;
    qtdLiq: number;
    //
    qtdTotal: number;
    vlrTotal: number;
    totalRejeitadas: number;
    totalReenviadas: number;
    totalPendentes: number;
    totalAcatadas: number;
    totalRejeicao: number;
    qtdFiltro:number;
    vlrFiltro:number;
    nomeFiltro:string;

    displayedColumns: string[] = [
        'select', 'CODIGO_EC', 'NOME_EC', 'CODIGO_PV', 'NOME_PV','CIDADE_PV', 'DATA_MOV', 'VALOR_VENDA', 'PARCELAS', 
        'QTD_DIAS_LIQ_FINAN_TRANS', 'NUM_REF_TRANS', 'COD_AUTH_TRANS', 'QTD_VZ_TRANS_REJ', 'COD_ERRO', 'DESC_MSG_ERRO', 'NUM_LOTE',
        'LOGIN_LIBERACAO', 'DATA_LIBERACAO', 'LOGIN_ACATADO', 'DATA_ACATADO', 'LOGIN_REJEICAO', 'DATA_REJEICAO'
    ];
    dataSource: any = null;//criando dataSource: utilizado para realizar o filtro
    selection = new SelectionModel<any>(true, []);
    
    objectRequest: ApiRequest<Reenvio>;
    private sub: Subscription[] = [];

    @ViewChild('chkTodos') private chkTodos: MatCheckbox;
    @ViewChild(MatSort) tableSort: MatSort;

    //filtro Transações
    opcFiltroSelect: string = 'T';
    exibirBtnExport: boolean = false; //variavel que controla a exibição do botao Exportar
    disabledFiltro: boolean = true; //variavel que controla se o Filtro (Todas, Rejeitadas, Liberadas...) esta Habilitado
    qtdTransPodeReenviar: number = 0;

    objectPesquisa: any[];

    constructor(
        private snackBar: MatSnackBar,
        private incomingService: IncomingService,
        private reenvioService: ReenvioService,
        private excelService: ExcelService,
        public dialog: MatDialog
    ) { }

    ngOnInit() {}

    ngOnDestroy() {
        //fazendo o unsubscribe de todos os subscription feitos
        this.sub.forEach(s => s.unsubscribe());
    }

    //cria o filtro por status da transação no DataSource
    criarFiltroTabela() {
        this.dataSource = new MatTableDataSource(this.listIncoming); //criando o DataSouce
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
    
    receberObjetoFiltro(objeto: any): void {
        this.resetPesquisa();
        this.objectPesquisa = objeto;
        this.sub.push(this.incomingService.getListIncoming(objeto).subscribe(v => {
           
            if (v.dados != null && v.dados.listIncoming != null && v.dados.listIncoming.length != 0){
              
                this.listIncoming = v.dados.listIncoming;
             
                this.criarFiltroTabela();
                // trocar para totaLiq

                this.qtdTotal = Number(v.dados.qtd);
                this.vlrTotal = Number(v.dados.valorTotal);
                this.valorLiq = this.vlrTotal;
               

                this.changeOpcFiltroTransacao(this.opcFiltroSelect);

                this.chkTodos.disabled = false;
                this.disabledFiltro = false;

                this.exibirBtnExport = true;
            } else {
                this.openSnackBar("Nenhum dado encontrado com esse filtro", "");
            }
        },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
        ));
    }

    //abra msg snackBar
    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
          duration: 4000,
        });
    }

    resetPesquisa(){
        this.selection.clear(); //limpo seleção
        this.listIncoming = null;
        this.dataSource = null; //como a grid esta vinculada ao datasouce, tenho que limpar ele para o conteudo da grid sumir
        // this.listIdIncomingRemover = []; --TIRAR
        this.qtdTotal = 0;
        this.vlrTotal = 0;
        this.chkTodos.checked = false;
        this.chkTodos.disabled = true;
        this.disabledFiltro = true;
        this.opcFiltroSelect = 'T';
        this.exibirBtnExport = false;
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

    onCheckItem(row: any){
        this.selection.toggle(row); // Marca/Desmarca linha
        /* --TIRAR
        if (!this.selection.isSelected(row)){ //verifica se linha desmarcada
            //desmarcada
            //como alterei o check da linha anterior, se esta desmarcada aqui, significa que antes era marcada
            //portanto, add na lista de remover

            //verifica se ja tem o id na lista Remover
            let idRemover = this.listIdIncomingRemover.indexOf(row.id);
            if(idRemover < 0) {
                //se nao tem, add
                this.listIdIncomingRemover.push(row.id);
            }
        }
        */
    }

    //verifica se um registro (transação) esta liberado
    estaLiberado(incoming: any) {
        return (incoming.loginLiberacao != null && incoming.loginLiberacao.trim() != "");
    }

    enviar() {
        //montando lista para Add
        var listIdIncomingAdicionar: any[] = [];
        this.selection.selected.forEach(e => {
            listIdIncomingAdicionar.push(e.id);
        });

        // if (this.listIdIncomingRemover.length <= 0 && listIdIncomingAdicionar.length <= 0) { --TIRAR
        if (listIdIncomingAdicionar.length <= 0) {
            this.openSnackBar("Nenhum dado de reenvio para ser atualizado", ":(");
        } else {
            let usuario: String = SharedService.getInstance().loggedUser.name;    
            let data: Date = new Date();
            let dataFormatada: String = data.getFullYear() + "-" + (data.getMonth()+1) + "-" + data.getDate(); //jan = 0; dez = 11

            let delay = 0;

            /* --TIRAR
            if (this.listIdIncomingRemover.length > 0) {
                this.objectRequest = <ApiRequest<Reenvio>> {
                    dados: {
                        "usuario": null,
                        "data": null,
                        "idsIncomings": this.listIdIncomingRemover
                    }
                }
                this.sub.push(this.reenvioService.getRespostaEnvio(this.objectRequest).subscribe(v => {
                    if (v.dados != null && v.dados.length != 0){
                        this.openSnackBar(v.dados, "Reenvio(s) Desabilitado(s)");
                    } else {
                        this.openSnackBar("Erro: nenhum reenvio desabilitado", "Reenvio(s) NÃO Desabilitado(s)");
                    }
                    delay = 3000;
                },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
                ));
            }
            */
            
            if (listIdIncomingAdicionar.length > 0) {
                this.objectRequest = <ApiRequest<Reenvio>> {
                    dados: {
                        "usuario": usuario,
                        "data": dataFormatada,
                        "idsIncomings": listIdIncomingAdicionar
                    }
                }
                this.sub.push(this.reenvioService.getRespostaEnvio(this.objectRequest).subscribe(v => {
                    setTimeout(() => {
                        if (v.dados != null && v.dados.length != 0){                        
                            this.openSnackBar(v.dados, "Reenvio(s) Habilitado(s)");
                        } else {                        
                            this.openSnackBar("Erro: nenhum reenvio habilitado", "Reenvio(s) NÃO Habilitado(s)");
                        }
                    }, delay);
                },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
                ));
            }

            this.resetPesquisa();
        }
    }

    //funcao disparada sempre que troco o filtro por status da Transação

    changeOpcFiltroTransacao(opcFiltro: string) {
        this.qtdTransPodeReenviar = 0;
        this.opcFiltroSelect = opcFiltro;//recebe a letra do filtro escolhido (T, L, A, R, P)
        
         //filtrando
         this.dataSource.filter = this.opcFiltroSelect;

         if (this.dataSource.filter === "T"){
                 //atualizando: QTD e VALOR total
                 this.qtdTotal = this.dataSource.filteredData.length
                 this.vlrTotal = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0); //somando valor

         } 

        this.chkTodos.disabled = true; //desabilitar a opção de Marcar / Desmarcar Todos
        //verificando se deve desabilitar a opção de Marcar/Desmarcar todos
        if (opcFiltro === 'L' || opcFiltro === 'A' || opcFiltro === 'R'){    
            this.chkTodos.checked = false;
        } else {
            if (this.dataSource.filteredData){
                this.dataSource.filteredData.forEach(e => {
                    if (e.podeMarcarParaReenviar) {
                        this.qtdTransPodeReenviar++;
                        this.chkTodos.disabled = false; //habilitando Marcar / Desmarcar Todos
                    }
                });
            }
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

        this.qtdFiltro = this.dataSource.filteredData.length
        this.vlrFiltro = this.dataSource.filteredData.map(t => t.valorVenda).reduce((acc, value) => acc + value, 0); //somando valor

    }

    //verifica se todos os itens da lista estão marcados
    isAllSelected() {
        if (this.dataSource == null){
            return false;
        }
        const numSelected = this.selection.selected.length;
        // const numRows = this.dataSource.filteredData.length;
        // return numSelected === numRows; //nao posso usar a qtd de itens filtrados no data source, pois nem todos os items podem ser marcados para reenvio
        return numSelected === this.qtdTransPodeReenviar;
    }

    isFiltroTodosOuPendentes(){
        return this.opcFiltroSelect === 'T' || this.opcFiltroSelect === 'P';
    }

    //funcao disparada quando clico no check de Marcar/Desmarcar Todos
    masterToggle() {
        if (this.isAllSelected()) { //esta tudo selecionado?
            //Sim...então limpo tudo
            this.selection.clear(); //limpo tudo

            //atualizando as listas
            // this.listIdIncomingRemover = []--TIRAR
            // this.dataSource.filteredData.forEach(row => this.listIdIncomingRemover.push(row.id)); //add todos os ID para remover--TIRAR

        } else {
            //nao esta tudo selecionado
            //entao, SELECIONO TUDO!
            this.dataSource.filteredData.forEach(row => {
                if (row.podeMarcarParaReenviar){
                    this.selection.select(row);
                }
            });

            //atualizando as listas
            // this.listIdIncomingRemover = []; //marquei todos para reenviar, entao nao tem nenhum que desejo remover--TIRAR
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

    exportarGridExcel():void {
        //ajustando a lista para exportar
        if (this.dataSource.filteredData == null || this.dataSource.filteredData.length == 0){
            this.openSnackBar("Nenhum dado encontrado com esse filtro", "");
        } else {
            //mesmo layout da tela de ConsultaAnalitica
            this.excelService.exporExcelConsultaAnalitica(this.dataSource.filteredData, this.objectPesquisa, "Reenvio", "Reenvio");
        }
    }

}