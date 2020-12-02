package com.deliveryit.desafio.service;

import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.deliveryit.desafio.entity.Pagamento;
import com.deliveryit.desafio.repository.PagamentoRepository;

@Component
public class PagamentoService {

	 private Double percentualMulta;
	 private Double percentualJurus; 
	 
	 @Autowired
	 private PagamentoRepository pagamentoRepository;
	
	public Pagamento cadastrarPagamento(Pagamento pagamento) {
		
		verificarPagamentoEmAtraso(pagamento);
		pagamentoRepository.save(pagamento);
		
		return pagamento;
		
	}

	private void verificarPagamentoEmAtraso(Pagamento pagamento) {
		long diasEmAtraso = pagamento.getDataVencimento().until(pagamento.getDataPagamento(), ChronoUnit.DAYS);
		
		if (diasEmAtraso > 0 && diasEmAtraso <=3) {
		   setPercentualMulta(2.0 / 100.0);
		   setPercentualJurus(0.1 / 100.0);
		   pagamento.setMulta("2%");
		   pagamento.setJurosDias("0,1%");
		   calcularValorCorrigido(pagamento, diasEmAtraso);
		   
		}
		
		if (diasEmAtraso > 3 && diasEmAtraso <=5) {
		  setPercentualMulta(3.0 / 100.0);
		  setPercentualJurus(0.2 / 100.0);
		  pagamento.setMulta("3%");
		  pagamento.setJurosDias("0,2%");
		  
		  calcularValorCorrigido(pagamento, diasEmAtraso);		
					
		}
		
		if (diasEmAtraso > 5) {
			setPercentualMulta(5.0 / 100.0);
			setPercentualJurus(0.3 / 100.0);
			pagamento.setMulta("5%");
			pagamento.setJurosDias("0,3%"); 
			calcularValorCorrigido(pagamento, diasEmAtraso);	
			
		}
	}

	private void calcularValorCorrigido(Pagamento pagamento, long diasEmAtraso) {
		pagamento.setValorCorrigido(pagamento.getValorOriginal() + (getPercentualMulta() * pagamento.getValorOriginal()));
		pagamento.setQuantidadeDiasAtraso(diasEmAtraso);   
		   for (int i = 0; i < diasEmAtraso; i++) {
			   pagamento.setValorCorrigido(pagamento.getValorCorrigido() + (getPercentualJurus() * pagamento.getValorCorrigido()));
			   
		   }
	}

	public Double getPercentualMulta() {
		return percentualMulta;
	}

	public void setPercentualMulta(Double percentualMulta) {
		this.percentualMulta = percentualMulta;
	}

	public Double getPercentualJurus() {
		return percentualJurus;
	}

	public void setPercentualJurus(Double percentualJurus) {
		this.percentualJurus = percentualJurus;
	}
	
	
	
}
