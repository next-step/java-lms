package nextstep.sessions.infrastructure;

import static nextstep.sessions.testFixture.SessionBuilder.aSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.time.LocalDateTime;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionRecruitingStatus;
import nextstep.sessions.domain.students.Student;
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

@JdbcTest
class SessionRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private SessionRepository sessionRepository;
  private UserRepository userRepository;

  @BeforeEach
  void setUp() {
    sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    userRepository = new JdbcUserRepository(jdbcTemplate);
  }

  @Test
  void saveAndFind() {
    Session session = aSession().build();
    int count = sessionRepository.save(session);
    assertThat(count).isEqualTo(1);
    Session savedSession = sessionRepository.findById(2L).orElseThrow();
    assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());
    LOGGER.debug("Session: {}", savedSession);
  }

  // TODO: autoIncrement라서 rollback시 pk가 날아가는 것 때문에 테스트하기 어려움
  // -> PK에 종속적이지 않게 테스트할 수 없을까?
  @Test
  void sessionRecruitingStatusUpdateAndFind() {
    Session session = aSession().build();
    int count = sessionRepository.save(session);
    assertThat(count).isEqualTo(1);

    Session savedSession = sessionRepository.findById(3L).orElseThrow();
    assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());

    savedSession.recruitStart();
    sessionRepository.update(savedSession);
    Session updatedSession = sessionRepository.findById(3L).orElseThrow();
    assertThat(updatedSession.getRecruitingStatus()).isEqualTo(SessionRecruitingStatus.RECRUITING);
  }


  // TODO: 시나리오 테스트이기 때문에 단위테스트가 아니라고 느껴짐
  // -> 레포지토리 테스트를 단위테스트처럼 만들 수 있는 방법은?
  @Test
  void userUpdateAndFind() {
    Session session = aSession().build();
    int count = sessionRepository.save(session);
    assertThat(count).isEqualTo(1);

    Session savedSession = sessionRepository.findById(1L).orElseThrow();
    savedSession.recruitStart();
    // data.sql에 의존되어 있음. 괜찮은가?
    NsUser user = userRepository.findByUserId("javajigi").orElseThrow();
    Student student = new Student(savedSession, user, LocalDateTime.of(2023, 6, 2, 13, 0), null);
    savedSession.enrollment(student);
    sessionRepository.update(savedSession);

    Session updatedSession = sessionRepository.findById(1L).orElseThrow();
    assertThat(updatedSession.getTitle()).isEqualTo(session.getTitle());
    assertThat(updatedSession.getStudents().stream()
        .filter(s -> s.isTaking(student))
        .findAny()
        .isPresent()).isTrue();
  }
}