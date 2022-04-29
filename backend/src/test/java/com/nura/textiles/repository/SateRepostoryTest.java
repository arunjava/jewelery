package com.nura.textiles.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.nura.jewelery.entity.address.State;
import com.nura.jewelery.repository.StateRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
public class SateRepostoryTest {

	@Autowired
	private StateRepository stateRepository;

	@Test
	void validatefindStateBsdOnCountryID() {
		List<State> states = stateRepository.findStateBsdOnCountryID(1l);
		
		System.out.println(states);
		
		assertThat(states.size() == 2);
	}

}
