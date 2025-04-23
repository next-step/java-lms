package nextstep.courses.domain;

import nextstep.courses.entity.CourseEntity;

public interface CourseRepository {
    Long save(CourseEntity courseEntity);

    CourseEntity findById(Long id);

    void delete(Long id);
}
