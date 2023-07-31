package ru.kata.spring.boot_security.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@SpringBootTest
class SpringBootSecurityDemoApplicationTests {
	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		User user = userService.getById(1L);
		System.out.println(user.getRoles());
	}

}
