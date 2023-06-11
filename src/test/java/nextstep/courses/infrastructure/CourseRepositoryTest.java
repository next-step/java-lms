package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.domain.batch.Batch;
import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class CourseRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private CourseRepository courseRepository;

  @BeforeEach
  void setUp() {
    courseRepository = new JdbcCourseRepository(jdbcTemplate);
  }

  @Test
  void crud() {
    // 저장
    Course course = new Course("TDD, 클린 코드 with Java", 1L);
    int count = courseRepository.save(course);
    assertThat(count).isEqualTo(1);

    Course savedCourse = courseRepository.findById(1L).get();
    assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());

    // 수정
    Batch batch = savedCourse.createdBatch(1L);
    count = courseRepository.save(savedCourse);
    assertThat(count).isEqualTo(1);

    Course updatedCourse = courseRepository.findById(1L).get();
    assertThat(savedCourse.getNowBatchNo()).isEqualTo(updatedCourse.getNowBatchNo());

    LOGGER.debug("Course: {}", savedCourse.getNowBatchNo());
  }
}
