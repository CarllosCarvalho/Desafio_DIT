package com.deliveryit.desafio.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.deliveryit.desafio.entity.Pagamento;
import com.deliveryit.desafio.repository.PagamentoRepository;
import com.deliveryit.desafio.service.PagamentoService;


@RestController
@RequestMapping("/pagamentos")
public class PagamentoController {

	@Autowired
	private PagamentoService pagamentoService;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	public PagamentoController(PagamentoService pagamentoService) {
		
	}

	@PostMapping(path = "/pagar")
	public ResponseEntity<Pagamento> cadastrar(@RequestBody @Valid Pagamento pagamento,UriComponentsBuilder uriBuilder) {
		pagamentoService.cadastrarPagamento(pagamento);
		URI uri = uriBuilder.path("/pagamentos/pagar").buildAndExpand(pagamento.getId()).toUri();
		return ResponseEntity.created(uri).body(pagamento);
	}
	
	@GetMapping("/listarPagamentos")
	public ResponseEntity<List<Pagamento>> consultarPagamentos() {
		
		List<Pagamento> contasCadastradas = pagamentoRepository.findAll();
		
		return ResponseEntity.ok(contasCadastradas);
	}
	
}
