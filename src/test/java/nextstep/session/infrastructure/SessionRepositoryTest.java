package nextstep.session.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.session.domain.*;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ImageInfoRepositoryTest.class);
	public static final Course C1 = new Course("title1", 1L);
	public static final Period P = new Period(LocalDateTime.now(), LocalDateTime.now().plusDays(1));
	public static final ImageInfo imageInfo = new ImageInfo(1L, new ImageSize(1024), new ImageReSolution(300, 200), ImageType.JPG);
	public static final Session session = new Session(C1.getCreatorId(), imageInfo, P, 50000, 50);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private SessionRepository sessionRepository;
	private ImageInfoRepository imageInfoRepository;
	private EnrollmentRepository enrollmentRepository;
	private UserRepository userRepository;

	@BeforeEach
	void setUp() {
		userRepository = new JdbcUserRepository(jdbcTemplate);
		enrollmentRepository = new JdbcEnrollmentRepository(jdbcTemplate, userRepository);
		imageInfoRepository = new JdbcImageInfoRepository(jdbcTemplate);
		sessionRepository = new JdbcSessionRepository(jdbcTemplate, imageInfoRepository, enrollmentRepository);
	}

	@Test
	void save() {
		assertThat(sessionRepository.save(session)).isEqualTo(1);
	}

	@Test
	void findById() {
		Optional<Session> session = sessionRepository.findById(1);
		assertThat(session.isEmpty()).isFalse();
		LOGGER.debug(session.get().toString());
	}

	@Test
	void update() {
		Session session = sessionRepository.findById(1).get();
		long newCourseId = session.getCourseId() + 1;
		Session newSession = new Session(session.getId(), newCourseId, session.getPeriod().getStartDate(), session.getPeriod().getEndDate());
		sessionRepository.update(newSession);
		assertThat(sessionRepository.findById(1).get().getCourseId()).isEqualTo(newCourseId);
	}

	@Test
	void deleteById() {
		assertThat(sessionRepository.deleteById(1)).isEqualTo(1);
	}

}