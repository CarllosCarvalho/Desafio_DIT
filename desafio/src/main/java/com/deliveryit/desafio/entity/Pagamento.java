package com.deliveryit.desafio.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="pagamentos")
public class Pagamento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull(message = "Campo nome de preenchimento obrigatório")
	@NotEmpty(message = "Campo nome de preenchimento obrigatório")
	private String nome;
	@NotNull(message = "Campo Valor Original de preenchimento obrigatório")
	private Double valorOriginal;
	@NotNull(message = "Campo Data de Vencimento de preenchimento obrigatório")
	private LocalDateTime dataVencimento;
	@NotNull(message = "Campo Data de Pagamento de preenchimento obrigatório")
	private LocalDateTime dataPagamento;
	
	private Double valorCorrigido;
	
	private Long quantidadeDiasAtraso;

	private String multa;
	
	private String jurosDias;
	
	
	public Pagamento() {
	
	}

	public Pagamento(
			@NotNull(message = "Campo nome de preenchimento obrigatório") @NotEmpty(message = "Campo nome de preenchimento obrigatório") String nome,
			@NotNull(message = "Campo Valor Original de preenchimento obrigatório") Double valorOriginal,
			@NotNull(message = "Campo Data de Vencimento de preenchimento obrigatório") LocalDateTime dataVencimento,
			@NotNull(message = "Campo Data de Pagamento de preenchimento obrigatório") LocalDateTime dataPagamento) {
		this.nome = nome;
		this.valorOriginal = valorOriginal;
		this.dataVencimento = dataVencimento;
		this.dataPagamento = dataPagamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getValorOriginal() {
		return valorOriginal;
	}

	public void setValorOriginal(Double valorOriginal) {
		this.valorOriginal = valorOriginal;
	}

	public LocalDateTime getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(LocalDateTime dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public LocalDateTime getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(LocalDateTime dataPagamento) {
		this.dataPagamento = dataPagamento;
	}

	public Double getValorCorrigido() {
		return valorCorrigido;
	}

	public void setValorCorrigido(Double valorCorrigido) {
		this.valorCorrigido = valorCorrigido;
	}

	public Long getQuantidadeDiasAtraso() {
		return quantidadeDiasAtraso;
	}

	public void setQuantidadeDiasAtraso(Long quantidadeDiasAtraso) {
		this.quantidadeDiasAtraso = quantidadeDiasAtraso;
	}

	public String getMulta() {
		return multa;
	}

	public void setMulta(String multa) {
		this.multa = multa;
	}

	public String getJurosDias() {
		return jurosDias;
	}

	public void setJurosDias(String jurosDias) {
		this.jurosDias = jurosDias;
	}
	
}
