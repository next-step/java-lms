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
}