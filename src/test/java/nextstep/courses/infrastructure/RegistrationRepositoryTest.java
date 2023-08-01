package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.registration.Registration;
import nextstep.courses.domain.registration.RegistrationRepository;
import nextstep.courses.domain.registration.Registrations;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionType;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@JdbcTest
public class RegistrationRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private UserRepository userRepository;

  private CourseRepository courseRepository;

  private SessionRepository sessionRepository;

  private RegistrationRepository registrationRepository;

  private Session session;

  private NsUser nsUser;

  @BeforeEach
  void setUp() {
    userRepository = new JdbcUserRepository(jdbcTemplate);
    nsUser = userRepository.findByUserId("javajigi").get();

    courseRepository = new JdbcCourseRepository(jdbcTemplate);
    Long saveId = courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));
    Course course = courseRepository.findById(saveId).get();

    sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    Session newSession = new Session("tdd", "tdd-img"
        , course.getId(), 1
        , LocalDateTime.now(), LocalDateTime.now().plusMonths(2)
        , SessionType.PAID, 10, 1L);
    newSession.recruitOpen();
    Long sessionId = sessionRepository.save(newSession);
    session = sessionRepository.findById(sessionId).get();

    registrationRepository = new JdbcRegistrationRepository(jdbcTemplate);
  }

  @Test
  @Transactional
  void create() {
    Registrations registrations = new Registrations(
        registrationRepository.findBySessionId(session.getId()));
    Registration registration = Registration.createRegistration(nsUser, session, registrations);

    Long saveId = registrationRepository.save(registration);

    Registration retRegistration = registrationRepository.findById(saveId).get();
    assertThat(retRegistration.getNsUserId()).isEqualTo(nsUser.getId());
    assertThat(retRegistration.getSessionId()).isEqualTo(session.getId());
  }

  @Test
  @Transactional
  void findBySessionId() {
    Registrations registrations = new Registrations(
        registrationRepository.findBySessionId(session.getId()));
    Registration registration = Registration.createRegistration(nsUser, session, registrations);
    registrationRepository.save(registration);

    Registration retRegistration = registrationRepository.findBySessionId(session.getId()).get(0);

    assertThat(retRegistration.getNsUserId()).isEqualTo(nsUser.getId());
    assertThat(retRegistration.getSessionId()).isEqualTo(session.getId());
  }

  @Test
  @Transactional
  void update() {
    Registrations registrations = new Registrations(
        registrationRepository.findBySessionId(session.getId()));
    Registration registration = Registration.createRegistration(nsUser, session, registrations);
    Long saveId = registrationRepository.save(registration);
    Registration savedRegistration = registrationRepository.findById(saveId).get();
    savedRegistration.cancel();

    Long updateId = registrationRepository.save(savedRegistration);

    Registration retRegistration = registrationRepository.findById(updateId).get();
    assertThat(retRegistration.isCancel()).isEqualTo(savedRegistration.isCancel());
  }
}
