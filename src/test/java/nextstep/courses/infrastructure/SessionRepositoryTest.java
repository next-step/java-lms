package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.time.LocalDateTime;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.SessionType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@JdbcTest
public class SessionRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private CourseRepository courseRepository;

  private SessionRepository sessionRepository;

  private Course course;

  @BeforeEach
  void setUp() {
    courseRepository = new JdbcCourseRepository(jdbcTemplate);
    Long saveId = courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));
    course = courseRepository.findById(saveId).get();

    sessionRepository = new JdbcSessionRepository(jdbcTemplate);
  }

  @Test
  @Transactional
  void create() {
    Session session = new Session("tdd", "tdd-img"
        , course.getId(), 1
        , LocalDateTime.now(), LocalDateTime.now().plusMonths(2)
        , SessionType.PAID, 10, 1L);
    Long saveId = sessionRepository.save(session);

    Session retSession = sessionRepository.findById(saveId).get();
    assertAll(
        () -> assertThat(retSession.getTitle()).isEqualTo(session.getTitle()),
        () -> assertThat(retSession.getImg()).isEqualTo(session.getImg())
    );
  }

  @Test
  @Transactional
  void update() {
    Session session = new Session("tdd", "tdd-img"
        , course.getId(), 1
        , LocalDateTime.now(), LocalDateTime.now().plusMonths(2)
        , SessionType.PAID, 10, 1L);
    Long saveId = sessionRepository.save(session);
    Session savedSession = sessionRepository.findById(saveId).get();

    savedSession.recruitOpen();
    Long updateId = sessionRepository.save(savedSession);

    Session retSession = sessionRepository.findById(updateId).get();
    assertThat(retSession.getSessionStatus()).isEqualTo(savedSession.getSessionStatus());
  }
}
