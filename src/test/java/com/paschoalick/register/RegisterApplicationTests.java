package com.paschoalick.register;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
class RegisterApplicationTests {

	@Mock
	private UserRepository userRepository;

	@InjectMocks // cria uma instância e coloca todos os mock nela
	private UserService userService;

	@Test
	void shoud_register_user_successfully() {
		User userBeforeSave = new User("Moises", "1234", LocalDate.of(1980, 8, 6));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		// Retorna um usuário com id
		Mockito.when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRepository).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);

		//String user;
		//String password;
		//Integer idade;
	}
	// barrar menores que 18 anos
	// o nome do usuário deve ter no mínimo 3 e no máximo 10 caracteres
	// a senha deve coter no mínimo 4 e no máximo 6 caracteres
}
