package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.session.registration.Registration;
import nextstep.courses.domain.session.registration.RegistrationRepository;

@JdbcTest
public class RegistrationTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private RegistrationRepository registrationRepository;

	@BeforeEach
	void setUp() {
		registrationRepository = new JdbcRegistrationRepository(jdbcTemplate);
	}

	@Test
	public void validate_save() {
		int count = registrationRepository.save(new Registration(1L, 1L));
		assertThat(count).isEqualTo(1);
	}

	@Test
	public void validate_findRegistrationsBySessionId() {
		registrationRepository.save(new Registration(1L, 1L));
		registrationRepository.save(new Registration(1L, 2L));
		registrationRepository.save(new Registration(2L, 2L));
		assertThat(registrationRepository.findRegistrationsBySessionId(2L).size()).isEqualTo(2L);
	}
}
