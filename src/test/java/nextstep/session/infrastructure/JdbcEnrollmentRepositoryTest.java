package nextstep.session.infrastructure;

import nextstep.session.domain.Enrollment;
import nextstep.session.domain.EnrollmentRepository;
import nextstep.session.domain.ImageInfo;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcEnrollmentRepositoryTest {
	public final static Enrollment CHANGE_E1 = new Enrollment(1L,1, 50000, 1L);
	public final static Enrollment E3 = new Enrollment(1, 50000);
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageInfoRepositoryTest.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private UserRepository userRepository;

	private EnrollmentRepository enrollmentRepository;

	@BeforeEach
	void setUp() {
		userRepository = new JdbcUserRepository(jdbcTemplate);
		enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate,userRepository);
	}

	@Test
	void save() {
		assertThat(enrollmentRepository.save(E3)).isEqualTo(1);
	}

	@Test
	void findById() {
		Optional<Enrollment> enrollment = enrollmentRepository.findById(1L);
		assertThat(enrollment.isEmpty()).isFalse();
		LOGGER.debug("enrollment: {}", enrollment.get());
	}

	@Test
	void update() {
		enrollmentRepository.update(CHANGE_E1);
		Optional<Enrollment> enrollment = enrollmentRepository.findById(1L);
		assertThat(enrollment.isEmpty()).isFalse();
		assertThat(enrollment.get().getSessionPrice()).isEqualTo(CHANGE_E1.getSessionPrice());
	}

	@Test
	void deleteById() {
		assertThat(enrollmentRepository.deleteById(1L)).isEqualTo(1);
	}

}