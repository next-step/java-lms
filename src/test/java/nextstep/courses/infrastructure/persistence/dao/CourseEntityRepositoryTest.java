package nextstep.courses.infrastructure.persistence.dao;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import nextstep.courses.infrastructure.persistence.entity.CourseEntity;
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
  void CourseEntity_저장하고_나온_rowId로_다시_찾는_경우_성공() {
    CourseEntity courseEntity = new CourseEntity("Course 3", 1L, "Third Generation");
    Long rowId = courseEntityRepository.save(courseEntity);
    assertThat(rowId).isEqualTo(1);

    assertThat(courseEntityRepository.findById(rowId)).isNotEmpty();
  }

  /**
   * VALUES (100, 'Course 1', 1,'First Generation', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
   */
  @Test
  void 임시_데이터에_존재하는_100L_Course_조회_성공() {
    Optional<CourseEntity> optionalCourseEntity = courseEntityRepository.findById(100L);
    assertThat(optionalCourseEntity).isNotNull();

    optionalCourseEntity.ifPresent(courseEntity -> {
      assertThat(courseEntity.getId()).isEqualTo(100L);
      assertThat(courseEntity.getTitle()).isEqualTo("Course 1");
      assertThat(courseEntity.getCreatorId()).isEqualTo(1L);
      assertThat(courseEntity.getGeneration()).isEqualTo("First Generation");
    });
  }

  @Test
  void 임시_데이터에_존재하지_않는_101L_Course_조회_실패() {
    Optional<CourseEntity> optionalCourseEntity = courseEntityRepository.findById(101L);
    assertThat(optionalCourseEntity).isEmpty();
  }
}