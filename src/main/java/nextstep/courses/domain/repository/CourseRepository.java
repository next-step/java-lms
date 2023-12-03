package nextstep.courses.domain.repository;

import nextstep.courses.domain.entity.NsCourse;

public interface CourseRepository {
    int save(NsCourse nsCourse);

    NsCourse findById(Long id);
}
