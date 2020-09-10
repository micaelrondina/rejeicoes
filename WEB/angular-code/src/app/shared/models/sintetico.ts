export interface Sintetico {
    dataOutgoing: string;
    dataOutgoingFormatada: string;
    nomeArqOutgoing: string;
    qtdOutgoing: number;
    valorTotalOutgoing: number;
    dataRetornoIncomingParcial?: string;
    dataRetornoIncomingParcialFormatada?: string;
    nomeArqIncomingParcial?: string;
    qtdIncomingParcial?: number;
    valorIncomingParcial?: number;
    dataRetornoIncomingTotal?: string;
    dataRetornoIncomingTotalFormatada?: string;
    nomeArqIncomingTotal?: string;
    qtdIncomingTotal?: number;
    valorIncomingTotal?: number;
    qtdLiquido: number;
    valorLiquido: number;
}