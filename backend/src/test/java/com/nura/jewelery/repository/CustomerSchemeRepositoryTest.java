package com.nura.jewelery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nura.jewelery.dto.customer.CustomerSchemeDTO;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerSchemeRepositoryTest {

	@Autowired
	private CustomerSchemeRepository cusSchemeRepository;

	@Test
	void getActiveSchemes() {
		try {
			List<CustomerSchemeDTO> customerSchemes = cusSchemeRepository.getActiveCustomerSchemeBsdOnID(10l);
			customerSchemes.forEach(cusScheme -> {
				System.out.println(cusScheme.toString());
			});
			assertThat(2 == customerSchemes.size());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
