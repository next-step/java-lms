package nextstep.courses.domain;

import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.fixture.TestFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

    @DisplayName("저장한다")
    @Test
    void save() {
        Course course = Course.of(5L,"TDD, 클린 코드 with Java", 1L);
        Course save = courseRepository.save(course);
        assertThat(save).isNotNull();
        Course savedCourse = courseRepository.findById(save.getCourseId().value()).orElseThrow();

        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());
        LOGGER.debug("Course: {}", savedCourse);
    }

    @DisplayName("조회한다")
    @Test
    public void findById() {
        //given
        Course save = courseRepository.save(TestFixture.K8S_COURSE);
        save.getCourseId().value();
        Course savedCourse = courseRepository.findById(1L).orElseThrow();

        //when
        //then
        System.out.println(savedCourse.toString());

    }

  @DisplayName("모든 데이터를 조회한다")
  @Test
  public void findAll() {
      //given
      courseRepository.save(TestFixture.K8S_COURSE);
      courseRepository.save(TestFixture.KOTLIN_COURSE);
      courseRepository.save(TestFixture.RUST_COURSE);
      //when
      List<Course> all = courseRepository.findAll();
      //then
      for (Course course : all) {
          System.out.println(course);
      }
  }
}
