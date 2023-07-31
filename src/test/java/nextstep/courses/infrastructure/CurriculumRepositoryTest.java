package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.batch.Batch;
import nextstep.courses.domain.batch.BatchRepository;
import nextstep.courses.domain.batch.Batches;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.curriculum.Curriculum;
import nextstep.courses.domain.curriculum.CurriculumRepository;
import nextstep.courses.domain.curriculum.Curriculums;
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
public class CurriculumRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private CourseRepository courseRepository;

  private BatchRepository batchRepository;

  private SessionRepository sessionRepository;

  private CurriculumRepository curriculumRepository;

  private Batch batch;

  private Session session;

  @BeforeEach
  void setUp() {
    courseRepository = new JdbcCourseRepository(jdbcTemplate);
    Long courseId = courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));
    Course course = courseRepository.findById(courseId).get();

    batchRepository = new JdbcBatchRepository(jdbcTemplate);
    Batches batches = new Batches(batchRepository.findByCourseId(course.getId()));
    Long batchId = batchRepository.save(course.createdBatch(1L, batches));
    batch = batchRepository.findById(batchId).get();

    sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    Long sessionId = sessionRepository.save(new Session("tdd", "tdd-img"
        , LocalDateTime.now(), LocalDateTime.now().plusMonths(2)
        , SessionType.PAID, 10, 1L));
    session = sessionRepository.findById(sessionId).get();

    curriculumRepository = new JdbcCurriculumRepository(jdbcTemplate);
  }

  @Test
  @Transactional
  void create() {
    Curriculums curriculums = new Curriculums(curriculumRepository.findByBatchId(batch.getId()));
    Curriculum curriculum = batch.addSession(session, curriculums, 1L);
    Long saveId = curriculumRepository.save(curriculum);

    Curriculum retCurriculum = curriculumRepository.findById(saveId).get();
    assertThat(retCurriculum.getBatchId()).isEqualTo(batch.getId());
    assertThat(retCurriculum.getSessionId()).isEqualTo(session.getId());
  }

  @Test
  @Transactional
  void findByBatchId() {
    Curriculums curriculums = new Curriculums(curriculumRepository.findByBatchId(batch.getId()));
    Curriculum curriculum = batch.addSession(session, curriculums, 1L);
    curriculumRepository.save(curriculum);

    Curriculum retCurriculum = curriculumRepository.findByBatchId(batch.getId()).get(0);
    assertThat(retCurriculum.getBatchId()).isEqualTo(batch.getId());
    assertThat(retCurriculum.getSessionId()).isEqualTo(session.getId());
  }
}
