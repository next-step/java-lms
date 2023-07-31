package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.batch.Batch;
import nextstep.courses.domain.batch.BatchRepository;
import nextstep.courses.domain.batch.Batches;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

@JdbcTest
public class BatchRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private CourseRepository courseRepository;

  private BatchRepository batchRepository;

  private Course course;

  @BeforeEach
  void setUp() {
    courseRepository = new JdbcCourseRepository(jdbcTemplate);
    Long courseId = courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));
    course = courseRepository.findById(courseId).get();

    batchRepository = new JdbcBatchRepository(jdbcTemplate);
  }

  @Test
  @Transactional
  void create() {
    Batches batches = new Batches(batchRepository.findByCourseId(course.getId()));
    Batch batch = course.createdBatch(1L, batches);
    Long saveId = batchRepository.save(batch);

    Batch retBatch = batchRepository.findById(saveId).get();
    assertThat(retBatch.getBatchNo()).isEqualTo(batch.getBatchNo());
  }

  @Test
  @Transactional
  void findByCourseId() {
    Batches batches = new Batches(batchRepository.findByCourseId(course.getId()));
    Batch batch = course.createdBatch(1L, batches);
    batchRepository.save(batch);

    Batch retBatch = batchRepository.findByCourseId(course.getId()).get(0);
    assertThat(retBatch.getCourseId()).isEqualTo(course.getId());
    assertThat(retBatch.getBatchNo()).isEqualTo(batch.getBatchNo());
  }
}
