import { Component, OnInit, OnDestroy, Output, EventEmitter, Input  } from '@angular/core';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ParametroService } from '../../shared/services/parametro.service';
import { FiltroConsultar } from '../../shared/models/filtro-consultar';
import { ApiRequest } from '../../shared/models/request/api-request';
import { SharedService } from '../../shared/shared.service';
import Utils from '../utils';

@Component({
    selector: 'filtro-consulta',
    templateUrl: './filtro-consulta.component.html',
    styleUrls: ['./filtro-consulta.component.css']
})
export class FiltroConsultaComponent implements OnInit, OnDestroy {
    @Input() lblTitulo: string = null; //Label titulo da card
    @Input() tipoConsulta: string = null;
    @Input() outrosTotais: boolean = false;
    @Input() qtdTotal: number = 0;
    @Input() vlrTotal: number = 0;
    @Input() qtdLiq: number = 0;
    @Input() valorLiq: number = 0;
    @Input() vlrFiltro: number = 0;
    @Input() qtdFiltro: number = 0;
    @Input() opcFiltroSelect: string= "N";
    @Input() nomeFiltro: string;
    @Input() exibirBtnExport: boolean = false; //se false, nao exibe nunca o botao de Export
    @Output() objectFiltro = new EventEmitter();
    @Output() changeSearch = new EventEmitter();
    @Output() onExport = new EventEmitter(); //event disparado quando clica no botao para exportar para excel
    objectRequest: ApiRequest<FiltroConsultar>;

    private sub: Subscription[] = [];
    formSearch: FormGroup;

    //listas de dados carregados backend
    listTpTransacao: any = null;
    listCredenciador: any = null;

    //Produtos - Fixo
    listProdutos: any = [
        {vlParametroAlfa: "C", dsParametro: "Crédito"},
        {vlParametroAlfa: "D", dsParametro: "Débito"}
    ];
    tpTransacaoSelectedDescricao: string = null;
    

    constructor(
        private _formBuilder: FormBuilder,
        private snackBar: MatSnackBar,
        private parametroService: ParametroService
    ) { }

    ngOnInit() {
        //criando form
        this.formSearch = this._formBuilder.group({
            idAcquirer: [this.getAdiquirente(), Validators.required],
            idProduto: [this.getProduto(), Validators.required],
            dataInicio: [this.getDataInicio(), Validators.required],
            dataFim: [this.getDataFim(), Validators.required],
            idTpTransacao: [this.getTpTransacao(), Validators.required],
            codErro: [this.getCodErro()]
        });

        //carregando valores do backend
        this.sub.push(this.parametroService.getCodTransacoes().subscribe(v => {
            this.listTpTransacao = v.dados;
            this.setTpTransacaoDefault();
        },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
        ));
        this.sub.push(this.parametroService.getCredeciadores().subscribe(v => {
            this.listCredenciador = v.dados
        },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
        ));
    }

    ngOnDestroy() {
        //fazendo o unsubscribe de todos os subscription feitos
        this.sub.forEach(s => s.unsubscribe());
    }

    clickExport(){
        this.onExport.emit(true);
    }

    changeParametros(): void {
        this.changeSearch.emit(true);
    }

    changeTpTransacao(event){
        this.tpTransacaoSelectedDescricao = event.source.selected._element.nativeElement.innerText.trim().split("-")[1].trim();
        this.changeParametros();
    }

    

    setTpTransacaoDefault(){
        if (this.listTpTransacao != null && this.listTpTransacao.length > 0) {
            this.listTpTransacao.forEach(tpTransacao => {
                if (tpTransacao.vlParametroAlfa === "01") { //01 - Rejeicao
                    this.formSearch.get('idTpTransacao').setValue(tpTransacao);
                    this.tpTransacaoSelectedDescricao = tpTransacao.dsParametro;
                }
            });
        }
    }

    onSubmitSearch(): void {
        if (!this.formSearch.valid) {
          this.openSnackBar("Dados Invalidos", ":(");
        } else {
            //validando se DataFim eh Maior ou Igual a Data Inicio
            if (this.formSearch.get('dataFim').value < this.formSearch.get('dataInicio').value) {
                this.openSnackBar("Dados Invalidos - Data Iní­cio deve ser menor ou igual Data Fim", ":(");
            } else {
                //tudo OK
                //Seta as informações no SharedService (essas informação são usadas para exportar os dados para Excel)
                SharedService.getInstance().infoAdqProdTpTrans = {
                    "idAcquirer":    this.formSearch.get('idAcquirer').value.vlParametroAlfa,
                    "acquirer":      this.formSearch.get('idAcquirer').value.dsParametro,
                    "idProduto":     this.formSearch.get('idProduto').value.vlParametroAlfa,
                    "produto":       this.formSearch.get('idProduto').value.dsParametro,
                    "idTpTransacao": this.formSearch.get('idTpTransacao').value.vlParametroAlfa,
                    "tpTransacao":   this.formSearch.get('idTpTransacao').value.dsParametro
                }
                this.objectRequest = <ApiRequest<FiltroConsultar>>{
                    filtro: {
                        "idAcquirer": this.formSearch.get('idAcquirer').value.vlParametroAlfa,
                        "idProduto": this.formSearch.get('idProduto').value.vlParametroAlfa,
                        "idTpTransacao": this.formSearch.get('idTpTransacao').value.vlParametroAlfa,
                        "dataInicio": this.formSearch.get('dataInicio').value,
                        "dataFim": this.formSearch.get('dataFim').value,
                        "codErro": this.formSearch.get('codErro').value
                    }
                }
                this.objectFiltro.emit(this.objectRequest);
            }
        }
    }

    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
          duration: 4000,
        });
    }

    consultaAnalitica() {
        return (this.tipoConsulta.localeCompare("analitica") == 0) ? true : false;
    }

    exibeOutrosTotais() {
        return this.outrosTotais && this.opcFiltroSelect=="R" || this.opcFiltroSelect=="L" || this.opcFiltroSelect=="A" || this.opcFiltroSelect=="P";
    }

    exibirBotaoExport() {
        return this.exibirBtnExport;
    }

    formataValor(valor: number) {
        return valor != null ? valor.toLocaleString('pt-br', {minimumFractionDigits: 2}) : '';
    }

    getRequiredMessage(campo: string) {
        return this.formSearch.get(campo).hasError('required') ? 'Campo Obrigatório!': '';
    }

    protected getAdiquirente(): number{
        return null;
    }

    protected getProduto(): number{
        return null;
    }

    protected getDataInicio(): number{
        return null;
    }

    protected getDataFim(): number{
        return null;
    }

    protected getTpTransacao(): number{
        return null;
    }

    protected getCodErro(): number{
        return null;
    }
}