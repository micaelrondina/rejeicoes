import { Component, OnInit, Output, EventEmitter, Input  } from '@angular/core';

@Component({
    selector: 'filtro-status-transacao',
    templateUrl: './filtro.status.transacao.component.html',
    styleUrls: ['./filtro.status.transacao.component.css']
})
export class FiltroStatusTransacaoComponent implements OnInit {
    @Input() opcFiltroSelect: string = 'T';
    @Input() disabledFiltro: boolean = true;
    @Output() changeFiltro = new EventEmitter();

    //opcoes do Filtro
    opcoesFiltroTrans: any = [
        {id: 'T', label: 'Todas'},
        {id: 'L', label: 'Reenviadas'}, //antes era Liberados, por isso o id = L
        {id: 'A', label: 'Acatadas'},
        {id: 'R', label: 'Rejeitadas'},
        {id: 'P', label: 'Pendentes'}
    ];

    constructor() { }

    ngOnInit() {}

    changeOpcFiltroTransacao(){
        this.changeFiltro.emit(this.opcFiltroSelect);
        
    }
}