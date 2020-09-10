export interface Incoming {
    cidadePV: string;
    codAuthTransacao: string;
    codErro: string;
    dataAcatado: any;
    dataLiberacao: any;
    dataMov: any;
    dataMovFormatada: string;
    dataRejeicao: any;
    descMsgErro: string;
    id: any;
    loginAcatado: string;
    loginLiberacao: string;
    loginRejeicao: string;
    nomePV: string;
    numLote: number;
    numParcela: number;
    numRefTransacao: string;
    qtdDiasLiqFinanTransacao: number;
    qtdTotParcelas: number;
    qtdVezesTransRej: number;
    valorVenda: number;
    nomeArquivo: string;
    nrRemessaRejeicao: string;
    nrRemessaAcatado: string;
    idTpTrans: string;
}