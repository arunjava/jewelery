package com.nura.textiles.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import com.nura.jewelery.entity.stock.StockIn;
import com.nura.jewelery.repository.StockInRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
@ActiveProfiles("dev")
class StockInRepositoryTest {

	@Autowired
	private StockInRepository stockInRepository;

	
	@Test
	void saveStockIn() {
		StockIn stockIn = stockInRepository.save(getStockIn());
		assertThat(stockIn.getBatch().equals(getStockIn().getBatch()));
	}

	public StockIn getStockIn() {
		StockIn stockIn = new StockIn();
		stockIn.setBarCode("BAR101");
		stockIn.setBatch("BATCH101");
//		stockIn.setProduct(productRepository.findById((long) 1));
		return stockIn;
	}
	
	
}
