import { IncomingService } from './../../services/incoming.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { MatSnackBar } from '@angular/material';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Batimento } from '../../../shared/models/batimento';
import { ApiRequest } from '../../../shared/models/request/api-request';
import Utils from '../../utils';

@Component({
    selector: 'app-consultar-arq-ret-payware',
    templateUrl: './retorno.arq.payware.component.html',
    styleUrls: ['./retorno.arq.payware.component.css']
})
export class ConsultarArqRetPaywareComponent implements OnInit, OnDestroy {
    private sub: Subscription[] = [];
    formSearch: FormGroup;
    listRetorno: any[];

    displayedColumns: string[] = ['DATA_MOV', 'NR_REF_TRANSACAO', 'NR_PARCELA', 'VALOR_VENDA', 'DH_ENVIO_ARQ_PAYWARE', 'RETURN_CODE_PAYWARE', 'RETURN_DESCRIPTION_PAYWARE'];

    constructor(
        private formBuilder: FormBuilder,
        private snackBar: MatSnackBar,
        private incomingService: IncomingService
    ) {}

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
            //Listando os Retornos
            let objectRequest = <ApiRequest<Batimento>>{
                filtro: {
                    "dataMovimento": this.formSearch.get('dataMov').value
                }
            }
            this.sub.push(this.incomingService.getIncomingRetArqPayware(objectRequest).subscribe(v => {
                if (v.dados != null && v.dados.length > 0){
                    this.listRetorno = v.dados;
                } else {
                    this.listRetorno = null;
                    this.openSnackBar("Nenhuma Transação Encontrada com essa Data!", "");
                }
            },(error: any) => this.openSnackBar(Utils.getError(error), "Ocorreu um erro!")
            ));            
        }
    }

    openSnackBar(message: string, action: string) {
        this.snackBar.open(message, action, {
          duration: 4000,
        });
    }

    changeDatMovi() {
        this.listRetorno = null;
    }

    getRequiredMessage(campo: string) {
        return this.formSearch.get(campo).hasError('required') ? 'Campo Obrigatório!': '';
    }

    protected getDataMov(): number{
        return null;
    }

}
