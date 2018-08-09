package com.bcopstein.APICalctaxas;

import java.util.LinkedList;
import java.util.List;
import java.lang.Math;

public class EstatisticaCliente {
	private LinkedList<Conta> listaDeContas;
	private LinkedList<Operacao> listaDeOperacoes;
	public EstatisticaCliente(Persistencia persistencia) {
		LinkedList<Conta> lst = new LinkedList<Conta>();
		LinkedList<Operacao> lstO = new LinkedList<Operacao>();
		for(Conta c: persistencia.loadContas().values) {
			listaDeContas.add(c);
		}
		listaDeContas = lst;
		for(Operacao p: persistencia.loadOperacoes().values) {
			listaDeOperacoes.add(p);
		}
		listaDeOperacoes = lstO;
	}
	
	public double saldoMedioMes(int nroConta,int mes,int ano) {
		LinkedList<Double> lst = new LinkedList<Double>();
		double saldoInic = 0.0;
		for(Conta c:listaDeContas) {
			if(c.getNumero() == nroConta) {
				saldoInic = c.getSaldo();
			}
		}
		for(Operacao p: listaDeOperacoes) {
			if(p.getMes() == mes && p.getAno() == ano && p.getNumeroConta() == nroConta) {
				if(p.getTipoOperacao() == 0) {
					lst.add(saldoInic-p.getValorOperacao());
					saldoInic = saldoInic - p.getValorOperacao();
				}
			}
		}
		double conta = 0.0;
		for(Double val: lst) {
			conta+= val;	
		}
		return (conta/lst.size());		 
	}
	
	public double saldoMedioNegativoMes(int nroConta,int mes,int ano) {
		double negativo = 0.0;
		double saldoInic = 0.0;
		for(Conta c:listaDeContas) {
			if(c.getNumero() == nroConta) {
				saldoInic = c.getSaldo();
			}
		}
		for(Operacao p: listaDeOperacoes) {
			if(p.getMes() == mes && p.getAno() == ano && p.getNumeroConta() == nroConta) {
				if(p.getTipoOperacao() == 0) {
					if(saldoInic - p.getValorOperacao() < 0) {
						negativo += Math.abs(saldoInic-p.getValorOperacao);
					}
				}
			}
		}
		return negativo;
	}

	
	public double valorMedioOperacoes(int nroConta,int mes,int ano) {
		double valorAbsolutoOp = 0.0;
		for(Operacao p: listaDeOperacoes) {
			if(p.getMes() == mes && p.getAno() == ano && p.getNumeroConta() == nroConta) {
				valorAbsolutoOp += p.getValorOperacao();
			}
		}
		return valorAbsolutoOp;
	}
	
	public List<Double> saldoMes (int nroConta,int mes,int ano){
		double saldoInic = 0.0;
		for(Conta c:listaDeContas) {
			if(c.getNumero() == nroConta) {
				saldoInic = c.getSaldo();
			}
		}
		for(Operacao p: listaDeOperacoes) {
			if(p.getMes() == mes && p.getAno() == ano && p.getNumeroConta() == nroConta) {
				saldoInic = saldoInic - p.getValorOperacao();
			}
		return saldoInic;		
		}
	}
}
