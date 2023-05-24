package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

import java.util.List;

public interface CourseService {
    long save(Course course);

    Course findById(long id);
}
