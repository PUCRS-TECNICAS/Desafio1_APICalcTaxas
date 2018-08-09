import java.util.ArrayList;

public class CustosConta
{
    EstatisticaCliente estatistica;
    PontuacaoCliente pontuacao;

    public CustosConta(EstatisticaCliente estatistica, 
                       PontuacaoCliente pontuacao){
        this.estatistica = estatistica;
        this.pontuacao = pontuacao;
    }

    public double jurosNoMes(int nroConta,int mes,int ano)
    {
        ArrayList<double> saldosNoMes = estatistica.saldoMes(nroConta, mes, ano);
        double jurosTotais = 0.0;

        for (double saldo : saldosNoMes)
        {
            if (saldo < 0) jurosTotais -= (saldo * 0.03);
        }    

        return jurosTotais;
    }

    public double taxaNoMes(int nroConta,int mes, int ano)
    {
        double pontuacaoAtual = pontuacao.calculaPontuacao(nroConta, mes, ano);
        double custo = 100;

        if (pontuacaoAtual > 20000) return 0;
        if (pontuacaoAtual > 10000) return custo * 0.5;
        if (pontuacaoAtual > 5000) return custo * 0.7;
        return custo;
    }
}