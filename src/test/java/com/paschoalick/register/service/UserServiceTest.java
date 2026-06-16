package com.paschoalick.register.service;

import com.paschoalick.register.domain.User;
import com.paschoalick.register.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

///@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks // cria uma instância e coloca todos os mock nela
	private UserService userService;

	@Test
	void should_register_user_successfully() {
		User userBeforeSave = new User(null, "Moises", "1234", LocalDate.of(1980, 8, 6));
		User userAfterSave = userBeforeSave;
		userAfterSave.setId("1");
		// Retorna um usuário com id
		Mockito.when(userRepository.save(userBeforeSave)).thenReturn(userAfterSave);

		var user = userService.register(userBeforeSave);

		Mockito.verify(userRepository).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);

	}
	// barrar menores que 18 anos
	// o nome do usuário deve ter no mínimo 3 e no máximo 10 caracteres
	// a senha deve coter no mínimo 4 e no máximo 6 caracteres
}
