package com.nura.jewelery.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.nura.jewelery.entity.User;
import com.nura.jewelery.repository.UserRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class UserRepositoryTest {

	@Autowired
	private UserRepository userRepo;

	@Test
	void testSaveUser() {
		User user = new User();
		user.setActive(true);
		user.setFirstName("Arun");
		user.setPassword("Venkatesan@25#");
		user.setUsername("arun@gmail.com");

		User savedUser = userRepo.save(user);

		System.out.println(savedUser.toString());

		assertThat(user.getUsername().equals(savedUser.getUsername()));

		assertThat(userRepo.getUserByUsername("arun@gmail.com").equals(user.getUsername()));
	}

}
