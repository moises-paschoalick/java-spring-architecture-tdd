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

		// Verifica se passou uma vez no save
		Mockito.verify(userRepository).save(userBeforeSave);
		Assertions.assertEquals(userAfterSave, user);

	}
	// barrar menores que 18 anos
	// o nome do usuário deve ter no mínimo 3 e no máximo 10 caracteres
	// a senha deve coter no mínimo 4 e no máximo 6 caracteres

	@Test
	public void should_return_error_when_age_is_less_than_eighteen() {
		User user = new User(null, "Danilo", "1234", LocalDate.of(2010, 4, 10));

		// Verifica se foi lançada uma excessão
		var exception = Assertions.assertThrows(RuntimeException.class, () -> userService.register(user));

		// Forma de ver se já pasosu no metodo
		// Mockito.verify(userRepository, Mockito.never()).save(user);

		// Verifica se não teve nenhuma interação com userRepository
		Mockito.verifyNoInteractions(userRepository);
		Assertions.assertEquals("Idade não permitida", exception.getMessage());


	}
}
