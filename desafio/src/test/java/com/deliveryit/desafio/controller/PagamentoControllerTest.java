package com.deliveryit.desafio.controller;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.deliveryit.desafio.entity.Pagamento;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class PagamentoControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;
	
		
	@Test
	void cadastrandoPagamento() {
		Pagamento pagamento = new Pagamento("Cadastro Pagamento",10.00,LocalDateTime.now(),LocalDateTime.now());
		HttpEntity<Pagamento> httpEntity = new HttpEntity<>(pagamento);
		ResponseEntity<Pagamento> resposta = testRestTemplate.exchange("/pagamentos/pagar", HttpMethod.POST,httpEntity,Pagamento.class);
		assertEquals(HttpStatus.CREATED, resposta.getStatusCode());
	}
	
	@Test
	void cadastrandoPagamentoCamposObrigatorios() {
		Pagamento pagamento = new Pagamento("Teste",null,null,null);
		HttpEntity<Pagamento> httpEntity = new HttpEntity<>(pagamento);
		ResponseEntity<List<String>> resposta = 
		  testRestTemplate.exchange("/pagamentos/pagar", HttpMethod.POST,httpEntity,new ParameterizedTypeReference<List<String>>(){});
		
		
		assertEquals(HttpStatus.BAD_REQUEST, resposta.getStatusCode());
		//assertTrue(resposta.getBody().contains("Campo nome de preenchimento obrigatório"));
		assertTrue(resposta.getBody().contains("Campo Valor Original de preenchimento obrigatório"));
		assertTrue(resposta.getBody().contains("Campo Data de Vencimento de preenchimento obrigatório"));
		assertTrue(resposta.getBody().contains("Campo Data de Pagamento de preenchimento obrigatório"));
	}
	
	@Test
	void deveListarTodosOsPagamentos() {
		ResponseEntity<String> resposta = testRestTemplate.exchange("/pagamentos/listarPagamentos", HttpMethod.GET,null,String.class);
		assertEquals(HttpStatus.OK, resposta.getStatusCode());
	}

}
