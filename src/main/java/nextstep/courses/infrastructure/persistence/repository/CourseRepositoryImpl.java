package nextstep.courses.infrastructure.persistence.repository;

import java.util.Optional;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.infrastructure.persistence.dao.CourseEntityRepository;
import nextstep.courses.infrastructure.persistence.entity.CourseEntity;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public class CourseRepositoryImpl implements CourseRepository {

  private final CourseEntityRepository courseEntityRepository;

  public CourseRepositoryImpl(CourseEntityRepository courseEntityRepository) {
    this.courseEntityRepository = courseEntityRepository;
  }

  @Override
  public Long save(Course course) {
    return courseEntityRepository.save(course.toEntity());
  }

  @Override
  public Course findById(Long id) {
    Optional<CourseEntity> courseEntity = courseEntityRepository.findById(id);
    return courseEntity.map(CourseEntity::toDomain).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강의입니다."));
  }
}
