package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class RegistrationRepositoryTest {
  private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private RegistrationRepository registrationRepository;

  private SessionRepository sessionRepository;

  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    sessionRepository = new JdbcSessionRepository(jdbcTemplate, new JdbcSessionImageRepository(jdbcTemplate));
    userRepository = new JdbcUserRepository(jdbcTemplate);
    registrationRepository = new JdbcRegistrationRepository(
            jdbcTemplate,
            sessionRepository,
            userRepository);
  }

  @Test
  void crud() {
    NsUser student = userRepository.findById(1L).get();
    SessionImage sessionImage = new SessionImage(300, 200, "jpg", 1024, "TEST", 1L);
    Session session = new ChargedSession(1L, LocalDate.now(), LocalDate.now().plusMonths(1L), List.of(sessionImage),
            OpenStatus.PREPARING, RecruitStatus.OPEN, 50, 800000L);
    sessionRepository.save(session);
    session = sessionRepository.findById(1L);
    Registration registration = new Registration(session, student);

    int count = registrationRepository.save(registration);
    assertThat(count).isEqualTo(1);

    Registration savedRegistration = registrationRepository.findById(1L);
    assertThat(registration.sessionId()).isEqualTo(savedRegistration.sessionId());
    savedRegistration = registrationRepository.findByUserIdAndSessionId(student.getId(), session.getId());
    assertThat(registration.sessionId()).isEqualTo(savedRegistration.sessionId());
    LOGGER.debug("Registration: {}", savedRegistration);

    List<Registration> savedRegistrations = registrationRepository.findByUserId(1L);
    assertThat(registration.sessionId()).isEqualTo(savedRegistrations.get(0).sessionId());
    LOGGER.debug("Registrations by userId: {}", savedRegistrations);

    savedRegistrations = registrationRepository.findBySessionId(1L);
    assertThat(registration.userId()).isEqualTo(savedRegistrations.get(0).userId());
    LOGGER.debug("Registrations by sessionId: {}", savedRegistrations);

//    jdbcTemplate.update("DELETE FROM session");
//    jdbcTemplate.update("DELETE FROM session_image");
  }
}
