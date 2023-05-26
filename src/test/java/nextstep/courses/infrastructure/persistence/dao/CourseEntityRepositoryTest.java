package nextstep.courses.infrastructure.persistence.dao;

import static org.assertj.core.api.Assertions.*;

import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.CourseEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

@JdbcTest
class CourseEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CourseEntityRepositoryTest.class);

  @Autowired
  private JdbcOperations jdbcOperations;

  private CourseEntityRepository courseEntityRepository;

  @BeforeEach
  void setUp() {
    courseEntityRepository = new CourseEntityRepository(jdbcOperations);
  }

  @Test
  void save() {
    CourseEntity courseEntity = new CourseEntity("Course 3", 1L, "Third Generation");
    int count = courseEntityRepository.save(courseEntity);
    assertThat(count).isEqualTo(1);
    LOGGER.debug("CourseEntity: {}", courseEntity);
  }

  @Test
  void findById() {
    Optional<CourseEntity> courseEntity = courseEntityRepository.findById(100L);
    assertThat(courseEntity).isNotNull();
    LOGGER.debug("CourseEntity: {}", courseEntity);
  }
}