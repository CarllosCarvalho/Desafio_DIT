package com.deliveryit.desafio.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deliveryit.desafio.entity.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

	
	
}
