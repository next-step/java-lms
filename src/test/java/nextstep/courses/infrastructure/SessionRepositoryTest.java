package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.entity.SessionEntity;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SessionRepositoryTest {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SessionRepository sessionRepository;

	private final SessionEntity sessionEntity = new SessionEntity(0L,
			"PREPARING",
			0L,
			LocalDateTime.of(2024, 4, 20, 0, 0, 0),
			LocalDateTime.of(2024, 5, 20, 0, 0, 0),
			0,
			0,
			0L);

	private Long id = 0L;

	@BeforeEach
	void setUp() {
		sessionRepository = new JdbcSessionRepository(jdbcTemplate);

		int count = sessionRepository.save(sessionEntity);
		assertThat(count).isEqualTo(1);
		id++;
	}

	@Test
	void 등록_후_조회() {
		assertThat(sessionRepository.findById(id))
				.usingRecursiveComparison()
				.ignoringFields("id")
				.isEqualTo(sessionEntity);
	}

	@Test
	void 학생_수_수정() {
		int numberOfStudent = sessionRepository.findById(id).getNumberOfStudent();

		sessionRepository.updateNumberOfStudent(id, numberOfStudent + 1);

		assertThat(sessionRepository.findById(id).getNumberOfStudent()).isEqualTo(numberOfStudent + 1);
	}
}
