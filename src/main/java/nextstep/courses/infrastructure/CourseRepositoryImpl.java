package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.infrastructure.entity.CourseEntity;
import nextstep.courses.infrastructure.jdbc.CourseJdbcDao;
import nextstep.courses.infrastructure.mapper.CourseMapper;
import org.springframework.stereotype.Repository;

@Repository("courseRepository")
public class CourseRepositoryImpl implements CourseRepository {
    private final CourseJdbcDao courseJdbcDao;

    public CourseRepositoryImpl(CourseJdbcDao courseJdbcDao) {
        this.courseJdbcDao = courseJdbcDao;
    }

    @Override
    public int save(Course course) {
        CourseEntity entity = CourseMapper.toEntity(course);
        return courseJdbcDao.save(entity);
    }

    @Override
    public Course findById(Long id) {
        CourseEntity entity = courseJdbcDao.findById(id);
        return CourseMapper.toDomain(entity);
    }
}