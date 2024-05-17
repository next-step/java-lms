package nextstep.courses.infrastructure;

import nextstep.courses.builder.PaidSessionBuilder;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SessionRepository sessionRepository;

	@BeforeEach
	void setUp() {
		sessionRepository = new JdbcSessionRepository(jdbcTemplate);
	}

	@Test
	void save() {
		Session session = new PaidSessionBuilder().build();
		int count = sessionRepository.save(session);

		assertThat(count).isEqualTo(1);
	}
}
