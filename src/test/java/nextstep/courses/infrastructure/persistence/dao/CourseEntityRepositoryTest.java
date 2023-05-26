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
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class CourseEntityRepositoryTest {

  private static final Logger LOGGER = LoggerFactory.getLogger(CourseEntityRepositoryTest.class);

  private final CourseEntityRepository courseEntityRepository;

  public CourseEntityRepositoryTest(@Autowired JdbcTemplate jdbcTemplate) {
    this.courseEntityRepository = new CourseEntityRepository(jdbcTemplate);
  }

  @Test
  void save() {
    CourseEntity courseEntity = new CourseEntity("Course 3", 1L, "Third Generation");
    Long rowId = courseEntityRepository.save(courseEntity);
    assertThat(rowId).isEqualTo(1);
    LOGGER.debug("CourseEntity: {}", courseEntity);
  }

  @Test
  void findById() {
    Optional<CourseEntity> courseEntity = courseEntityRepository.findById(100L);
    assertThat(courseEntity).isNotNull();
    LOGGER.debug("CourseEntity: {}", courseEntity);
  }
}