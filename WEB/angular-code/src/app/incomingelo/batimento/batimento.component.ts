import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ApiRequest } from '../../shared/models/request/api-request';
import { BatimentoService } from '../services/batimento.service';
import { Incoming } from '../../shared/models/incoming';
import { Batimento } from './../../shared/models/batimento';
import { ActivatedRoute } from '@angular/router';
import Utils from '../utils';

@Component({
    selector: 'app-batimento',
    templateUrl: './batimento.component.html',
    styleUrls: ['./batimento.component.css']
})
export class BatimentoComponent implements OnInit, OnDestroy {
    private sub: Subscription[] = [];
    formSearch: FormGroup;
    objectRequest: ApiRequest<Batimento>;
    listBatimento: Incoming[] = null;
    isModoRejeitado: boolean = true;
    lblModo: string = "Acatado";

    displayedColumns: string[] = [
        'NOME_PV','CIDADE_PV', 'DATA_MOV', 'VALOR_VENDA', 'PARCELAS', 'NUM_LOTE', 'NUM_REF_TRANS',
        'LOGIN_LIBERACAO', 'DATA_LIBERACAO', 'LOGIN_ACATADO', 'DATA_ACATADO', 'NUM_LOTE_ACATADO', 'LOGIN_REJEICAO', 'DATA_REJEICAO' , 'NUM_LOTE_REJEICAO'
    ];

    //indica se esta ou nao realizando o processamento do batimento
    processandoBatimento: boolean = false;

    constructor(
        private formBuilder: FormBuilder,
        private snackBar: MatSnackBar,
        private batimentoService: BatimentoService,
        private route: ActivatedRoute
    ) {
        //verificando o modo de execucao - lendo valores da rota
        this.route.data.subscribe((data) => {
            this.isModoRejeitado = data.isModoRejeitado;
        });
        if (this.isModoRejeitado) {
            this.lblModo = "Rejeitado";
        }
    }

    ngOnInit() {
        //criando form
        this.formSearch = this.formBuilder.group({
            dataMov: [this.getDataMov(), Validators.required]
        });

        //Setando a data Atual
        this.formSearch.controls['dataMov'].setValue(new Date());
    }

    ngOnDestroy() {
        this.sub.forEach(s => s.unsubscribe());
    }

    onSubmitSearch(): void {
        if (!this.formSearch.valid) {
            this.openSnackBar("Dados Invalidos", ":(");
        } else {
            this.montarObjectRequest();
            //Listando os batimentos
            this.sub.push(this.batimentoService.getListBatimento(this.objectRequest).subscribe(v => {
                if (v.dados != null && v.dados.length > 0){
                    this.listBatimento = v.dados;
                } else {
                    this.listBatimento = null;
                    this.openSnackBar("Nenhum Batimento Encontrado com essa Data!", "");
                }
            },(error: any) => this.openSnackBar(Utils.getError(error), "Erro ao Processar Batimento de Rejeitados!")
            ));            
        }
    }

    processarBatimento() {
        this.montarObjectRequest();
        this.processandoBatimento = true;
        if (this.isModoRejeitado){
            //processando batimentos Acatados
            this.sub.push(this.batimentoService.processarBatimentoRejeitado(this.objectRequest).subscribe(v => {
                if (v.dados != null) {
                    this.openSnackBar(v.dados, "Sucesso");
                } else {
                    this.openSnackBar(v.falha.message, "Erro ao Processar Batimento de Rejeitados!");
                }
                this.processandoBatimento = false;
            },(error: any) => this.openSnackBar(Utils.getError(error), "Erro ao Processar Batimento de Rejeitados!")
            ));
        } else {
            //processando batimentos Acatados
            this.sub.push(this.batimentoService.processarBatimentoAcatado().subscribe(v => {
                if (v.dados != null) {
                    this.openSnackBar(v.dados.mensagem, "Sucesso");

                    var requestListIncoming = <ApiRequest<string>>{
                        filtro: v.dados.idsDosItemsMarcados
                    }

                    if (v.dados.qtdItensMarcadosComoAcatados != null && v.dados.qtdItensMarcadosComoAcatados != 0){
                        this.sub.push(this.batimentoService.getListBatimentoById(requestListIncoming).subscribe( v => {
                            if (v.dados != null && v.dados.length > 0){
                                this.listBatimento = v.dados;
                            } else {
                                this.listBatimento = null;
                            }
                        }))
                    }
                } else {
                    this.openSnackBar(v.falha.message, "Erro ao Processar Batimento de Acatados!");
                }
                this.processandoBatimento = false;
            },(error: any) => this.openSnackBar(Utils.getError(error), "Erro ao Processar Batimento de Acatados!")
            ));
        }
    }

    private montarObjectRequest(){
        this.objectRequest = <ApiRequest<Batimento>>{
            filtro: {
                "dataMovimento": this.formSearch.get('dataMov').value
            }
        }
    }

    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
          duration: 4000,
        });
    }

    changeDatMovi() {
        this.listBatimento = null;
    }

    getRequiredMessage(campo: string) {
        return this.formSearch.get(campo).hasError('required') ? 'Campo Obrigatório!': '';
    }

    protected getDataMov(): number{
        return null;
    }
}
