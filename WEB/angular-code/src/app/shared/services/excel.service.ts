import { SharedService } from './../shared.service';
import { Injectable } from '@angular/core';
import * as FileSaver from 'file-saver';
import * as XLSX from 'xlsx';

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';

//service responsavel por exportar os dados para excel
@Injectable()
export class ExcelService {
    //Consulta Sintetica Header Linha 1 (as colunas vazias existem pq depois e feito merge)
    consultaSinteticaHeaderL1: any = [
        {A: "Outgoing", B:"", C:"", D:"",
         E: "Incoming ELO", F:"", G:"",
         H:"Incoming Emissor", I:"", J:"",
         K:"Líquido"}
    ]
    consultaSinteticaHeaderL2: any = [
        {A: "Data", B: "Arquivo", C:"Qtd.", D:"Total", E:"Data Retorno", F:"Qtd.", G:"Total",
        H:"Data Retorno", I:"Qtd.", J:"Total", K:"Qtd.", L:"Total"}
    ]

    //Consulta Analítica - Header
    consultaAnaliticaHeader: any = [
        {
            A: "Código EC", B: "Nome EC", C: "Código PV", D: "Nome PV",
            E: "Cidade PV", F:"Movimento", G:"Valor Venda", H:"Parcelas", I:"Dias Liq. Fin. Transação", J:"Nº Ref. Transação",
            K:"Cod. auth. Transação", L:"Qtd. Vezes. Trans. Rej.", M:"Cod. Erro", N:"Descrição Mensagem Erro", O:"Nº Lote", 
            P:"Usuário Reenvio", Q:"Data Reenvio", R:"Usuário Acatado", S:"Data Acatado", T:"Usuário Rejeição", U:"Data Rejeição"
        }
    ]

    constructor() { }

    /*
    Nao utilizado. Apenas Exemplo de como gerar Excel a partir de uma tabela
    public exportAsExcelFileFromTable(buffer: any, fileName: string, table: any): void {
        const ws: XLSX.WorkSheet=XLSX.utils.table_to_sheet(table);
        const wb: XLSX.WorkBook = XLSX.utils.book_new();
        XLSX.utils.book_append_sheet(wb, ws, 'Sheet1');
        
        // save to file
        XLSX.writeFile(wb, fileName + '_' + this.montaDateYYYYmmdd_HHmmss() + EXCEL_EXTENSION);
    }
    */

    private saveAsExcelFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], {
            type: EXCEL_TYPE
        });
        FileSaver.saveAs(data, fileName + '_' + this.montaDateYYYYmmdd_HHmmss() + EXCEL_EXTENSION);
    }

    private montaDateYYYYmmdd_HHmmss():any {
        var dt = new Date().toLocaleString("BR", {timeZone: "America/Recife"})
        var retorno: string = 
            dt.substring(6,10) //ano
            + dt.substring(3,5) //mes
            + dt.substring(0,2) //dia
            + "_"
            + dt.substring(11).replace(":","").replace(":",""); //hora minuto segundo

        return retorno;
    }

    /* Utilizando o Date, esta com problema no Horario de Verao
    private montaDateYYYYmmdd_HHmmss():any {
        var dt = new Date();
        var retorno: string = 
            dt.getFullYear().toString() //ano
            + (dt.getMonth()<10 ? '0' : '') + (dt.getMonth()+1).toString() //mes (janeiro eh mes 0; dez=11)
            + (dt.getDate()<10 ? '0' : '') + dt.getDate().toString() //dia
            + "_" 
            + (dt.getHours()<10 ? '0' : '') + dt.getHours().toString() //horas
            + (dt.getMinutes()<10 ? '0' : '') + dt.getMinutes().toString() //minutos
            + (dt.getSeconds()<10 ? '0' : '') + dt.getSeconds().toString() //segundos

        return retorno;
    }
    */

    private verificarRejTotal(dado: any, rejTotal: any){
        if (rejTotal == 1){
            return 'Rejeição Total';
        } else {
            return dado;
        }
    }

    //funcao para exportar para excel os dados da tela Consulta Sintetica
    public exporExcelConsultaSintetica(dados: any[], infoFiltroConsulta: any): void {
        //antes de chamar essa funcao, ja validei se DADOS tem conteudo
        var dadosExpor = new Array();
        dados.forEach(e => {

            //ajuste para quando for ST_REMESSA = 'R' - Rejeitado Total, exibir a msg de Rejeicao Total no excel exportado
            var obj = {
                A:e.dataOutgoingFormatada, B:e.nomeArqOutgoing, C:e.qtdOutgoing, D: e.valorTotalOutgoing, //Outgoing

                //Incoming ELO
                E:this.verificarRejTotal(e.dataRetornoIncomingParcialFormatada, e.rejTotal), 
                F:this.verificarRejTotal((e.dataRetornoIncomingParcialFormatada != null ? e.qtdIncomingParcial : ''), e.rejTotal),
                G:this.verificarRejTotal(e.valorIncomingParcial, e.rejTotal),

                //Incoming Emissor
                H:this.verificarRejTotal(e.dataRetornoIncomingTotalFormatada, e.rejTotal), 
                I:this.verificarRejTotal((e.dataRetornoIncomingTotalFormatada != null ? e.qtdIncomingTotal : ''), e.rejTotal), 
                J:this.verificarRejTotal(e.valorIncomingTotal, e.rejTotal),
                K:(e.rejTotal == 1 ? '-' : e.qtdLiquido), LN:(e.rejTotal == 1 ? '-' : e.valorLiquido)} //Líquido
            dadosExpor.push(obj);
        });

        //
        const worksheet: XLSX.WorkSheet = this.addCabecalhoInfoFiltro("Consulta Sintética", infoFiltroConsulta, false);

        /* Initial Header */
        const merge = [
            { s:{r:4, c: 0},  e:{r: 4, c: 3}  },
            { s:{r:4, c: 4},  e:{r: 4, c: 6}  },
            { s:{r:4, c: 7},  e:{r: 4, c: 9}  },
            { s:{r:4, c: 10}, e:{r: 4, c: 11} }
        ]; //s = start, r = row, c=col, e= end
        worksheet["!merges"] = merge;

        /* Write data */
        XLSX.utils.sheet_add_json(worksheet, this.consultaSinteticaHeaderL1, {skipHeader: true, origin: "A5"}); //Header linha1
        XLSX.utils.sheet_add_json(worksheet, this.consultaSinteticaHeaderL2, {skipHeader: true, origin: "A6"}); //Header linha2
        XLSX.utils.sheet_add_json(worksheet, dadosExpor, {skipHeader: true, origin: "A7"}); //Dados em si (começando na celula A3)

        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });

        this.saveAsExcelFile(excelBuffer, "Consulta_Sintetica");
    }

    public exporExcelConsultaAnalitica(dados: any[], infoFiltroConsulta: any, titulo: string, nomeArquivo: string): void {
        //ajustando a lista para exportar dados (visto que nao são todas as colunas de desejo exportar, monto uma nova lista apenas com as informações que desejo exportar)
        var listaAjustada: any[] = new Array();
        dados.forEach(e => {
            var parcelas = e.numParcela + "/" + e.qtdTotParcelas
            var obj = {
                A:e.codEC, B:e.nomeEC, C:e.codPV, D:e.nomePVBanco,
                E:e.cidadePV, F:e.dataMovFormatada, G:e.valorVenda, H:parcelas, I:e.qtdDiasLiqFinanTransacao, J:e.numRefTransacao,
                K:e.codAuthTransacao, L:e.qtdVezesTransRej, M:e.codErro, N:e.descMsgErro, O:e.numLote, 
                P:e.loginLiberacao, Q:e.dataLiberacao, R:e.loginAcatado, S:e.dataAcatado, T:e.loginRejeicao, U:e.dataRejeicao
            }
            listaAjustada.push(obj);
        });
        //
        const worksheet: XLSX.WorkSheet = this.addCabecalhoInfoFiltro(titulo, infoFiltroConsulta, true);

        /* Initial Header */
        XLSX.utils.sheet_add_json(worksheet, this.consultaAnaliticaHeader, {skipHeader: true, origin: "A5"});

        /* Write data starting at A2 */
        XLSX.utils.sheet_add_json(worksheet, listaAjustada, {skipHeader: true, origin: "A6"});

        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });

        this.saveAsExcelFile(excelBuffer, nomeArquivo);
    }

    private addCabecalhoInfoFiltro(titulo: string, infoFiltroConsulta: any, isAnalitica: boolean): XLSX.WorkSheet{
        var info: any = SharedService.getInstance().infoAdqProdTpTrans;
        //cabeçalho com as informações do filtro
        const worksheet: XLSX.WorkSheet =  XLSX.utils.json_to_sheet([{A:titulo}], {skipHeader: true}); //Linha 1

        //Linha2
        XLSX.utils.sheet_add_json(worksheet, [{A:"Adquirente: "}]                                   , {skipHeader: true, origin: "A2"}); //Label Adquirente
        XLSX.utils.sheet_add_json(worksheet, [{A: info.idAcquirer + " - " + info.acquirer}]         , {skipHeader: true, origin: "B2"}); //Adquirente
        XLSX.utils.sheet_add_json(worksheet, [{A:"Produto: "}]                                      , {skipHeader: true, origin: "C2"}); //Label Produto
        XLSX.utils.sheet_add_json(worksheet, [{A: info.produto}]                                    , {skipHeader: true, origin: "D2"}); //Produto
        if (isAnalitica){
            XLSX.utils.sheet_add_json(worksheet, [{A:"Tipo Transação: "}]                               , {skipHeader: true, origin: "E2"}); //Label Tipo Transação
            XLSX.utils.sheet_add_json(worksheet, [{A: info.idTpTransacao + " - " + info.tpTransacao}]   , {skipHeader: true, origin: "F2"}); //Tipo Transação
        }

        //Linha3
        XLSX.utils.sheet_add_json(worksheet, [{A:"Periodo: de "}]                                           , {skipHeader: true, origin: "A3"}); //Label Periodo de
        XLSX.utils.sheet_add_json(worksheet, [{A:infoFiltroConsulta.filtro.dataInicio.toLocaleDateString()}], {skipHeader: true, origin: "B3"}); //Periodo de
        XLSX.utils.sheet_add_json(worksheet, [{A:" a "}]                                                    , {skipHeader: true, origin: "C3"}); //Label a
        XLSX.utils.sheet_add_json(worksheet, [{A:infoFiltroConsulta.filtro.dataFim.toLocaleDateString()}]   , {skipHeader: true, origin: "D3"}); //Periodo de
        if (isAnalitica){
            XLSX.utils.sheet_add_json(worksheet, [{A:"Cod. Erro: "}]                                            , {skipHeader: true, origin: "E3"}); //Label Cod. Erro
            XLSX.utils.sheet_add_json(worksheet, [{A:infoFiltroConsulta.filtro.codErro}]                        , {skipHeader: true, origin: "F3"}); //Cod. Erro
        }
        
        return worksheet;
    }
}