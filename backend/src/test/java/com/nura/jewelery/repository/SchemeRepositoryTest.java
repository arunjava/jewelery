package com.nura.jewelery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.nura.jewelery.entity.offers.Offer;
import com.nura.jewelery.entity.scheme.Scheme;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SchemeRepositoryTest {

	@Autowired
	private SchemeRepository schemeRepository;

	@Test
	void validateOffersBsdOnSchemeID() {
		try {
			Scheme scheme = schemeRepository.getOffersBsdOnSchemeID(1l);
			List<Offer> offers = scheme.getOffers();
			System.out.println(offers);
			offers.forEach(offer -> {
				System.out.println(offer.getDesc());
			});
			assertThat(offers.size() == 2);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}
