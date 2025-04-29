package nextstep.courses.domain;

import nextstep.courses.entity.CourseEntity;

public interface CourseRepository {
    Long save(CourseEntity courseEntity);

    void delete(Long id);
}
