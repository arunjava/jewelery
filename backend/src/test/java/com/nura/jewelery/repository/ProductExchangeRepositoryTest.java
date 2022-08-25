package com.nura.jewelery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.nura.jewelery.dto.ProductExchangeDTO;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@ActiveProfiles("dev")
class ProductExchangeRepositoryTest {

	@Autowired
	private ProductExchangeRepository productExchangeRepository;

	
	@Test
	void exchangeVal() {
		List<ProductExchangeDTO> prodExchange = productExchangeRepository.getExhangeValBsdOnCustomerID(41l);
		System.out.println(prodExchange);
		assertThat(prodExchange.size() == 1);
	}
	
	
}
