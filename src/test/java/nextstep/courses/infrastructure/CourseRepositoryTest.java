package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.batch.Batch;
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
public class CourseRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private CourseRepository courseRepository;

  @BeforeEach
  void setUp() {
    courseRepository = new JdbcCourseRepository(jdbcTemplate);
  }

  @Test
  @Transactional
  void create() {
    Course course = new Course("TDD, 클린 코드 with Java", 1L);
    Long saveId = courseRepository.save(course);

    Course retCourse = courseRepository.findById(saveId).get();
    assertThat(retCourse.getTitle()).isEqualTo(course.getTitle());
  }

  @Test
  @Transactional
  void update() {
    Course course = new Course("TDD, 클린 코드 with Java", 1L);
    Long saveId = courseRepository.save(course);
    Course savedCourse = courseRepository.findById(saveId).get();

    Batch batch = savedCourse.createdBatch(1L, new Batches());
    Long updateId = courseRepository.save(savedCourse);

    Course retCourse = courseRepository.findById(updateId).get();
    assertThat(retCourse.getNowBatchNo()).isEqualTo(savedCourse.getNowBatchNo());
  }
}
