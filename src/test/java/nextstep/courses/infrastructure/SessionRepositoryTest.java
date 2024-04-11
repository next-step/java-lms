package nextstep.courses.infrastructure;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;

import java.time.LocalDate;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.ImageType;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.Session.SessionDuration;
import nextstep.courses.domain.SessionPayType;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionState;
import nextstep.courses.domain.SessionStudent;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class SessionRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private SessionRepository sessionRepository;
  private Session session;

  @BeforeEach
  void setUp() {
    sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    session = new Session(new Course("TDD, 클린 코드 with Java", 1L),  new SessionDuration(
        LocalDate.of(2025,4,11), LocalDate.of(2025,5,12)), new Image(100, ImageType.JPG, 300, 200), SessionPayType.FREE, SessionState.PREPARING, 0, 0L, new SessionStudent());
  }

  @Test
  @DisplayName("수업 Entity를 DB에 저장한다.")
  void saveSession() {
    Session savedSession = sessionRepository.save(session);
    Session sessionByDb = sessionRepository.findById(savedSession.getId());
    assertThat(savedSession.getId()).isEqualTo(sessionByDb.getId());
  }

  @Test
  @DisplayName("수업에 참여하는 학생을 등록한다.")
  void saveStudents() {
    Session savedSession = sessionRepository.save(session);
    Session sessionByDb = sessionRepository.findById(savedSession.getId());

    sessionByDb.openRegister();
    sessionByDb.addStudent(JAVAJIGI, new Payment(20L, savedSession.getId(), 1L, 10000L));

    assertThatNoException().isThrownBy(() -> sessionRepository.saveStudents(sessionByDb));
  }
}
