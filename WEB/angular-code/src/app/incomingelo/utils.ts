import { HttpErrorResponse } from "@angular/common/http/src/response";
import { Sort, MatSort } from "@angular/material/sort";

//class de funcoes uteis
export default class Utils {

    static getError(error: HttpErrorResponse): string {
        console.log(error);
        if (error.status > 0) {
          return "Erro " + error.error.falha.message
        } else {
          return error.status + "-" + error.statusText;
        }
    }

    //cria o filtro por status da transação no DataSource
    static filterStatusTransacoes(data: any, filter: string): boolean {
        if (filter === "T") { //TODAS
            return true;
        } else if (filter === "L") { //LIBERADAS
            // return data.dataLiberacao != null || data.dataAcatado != null || data.dataRejeicao != null;
            return data.dataLiberacao != null && data.dataAcatado == null && data.dataRejeicao == null;
        } else if (filter === "A") { //ACATADAS
            return data.dataAcatado != null;
        } else if (filter === "R") { //REJEITADAS
            return data.dataRejeicao != null;
        } else if (filter === "P") { //PENDENTES
            return data.dataLiberacao == null && data.dataAcatado == null && data.dataRejeicao == null;
        }
    };

    static compare(a: number | string, b: number | string, isAsc: boolean) {
        return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
    }

    static ordenarLista(dataSource: any, sort: Sort) {
        if (dataSource == null){
            return;
        }

        //se nao tem ordenacao, retorna o DataSource
        if (!sort.active || sort.direction === '') {
            // return dataSource.filteredData;
            return dataSource;
        }

        // const data = dataSource.filteredData.slice();
        const data = dataSource.slice();

        //ordenando
        var dataSourceFilteredDataOrdened = data.sort((a, b) => {
            const isAsc = sort.direction === 'asc';
            switch (sort.active) {
                case 'CODIGO_EC': return Utils.compare(a.codEC, b.codEC, isAsc);
                case 'NOME_EC': return Utils.compare(a.nomeEC, b.nomeEC, isAsc);
                case 'CODIGO_PV': return Utils.compare(a.codPV, b.codPV, isAsc);
                case 'NOME_PV': return Utils.compare(a.nomePVBanco, b.nomePVBanco, isAsc);
                case 'NUM_REF_TRANS': return Utils.compare(a.numRefTransacao, b.numRefTransacao, isAsc);
                case 'COD_ERRO': return Utils.compare(a.codErro, b.codErro, isAsc);
                default: return 0;
            }
        });
        return dataSourceFilteredDataOrdened;
    }

    static removerOrdenacaoTable(tableSort: MatSort) {
        tableSort.sort({id: "", start: 'asc', disableClear: false}); //tirando a ordenacao
    }

}