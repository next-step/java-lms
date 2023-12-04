package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.users.domain.NsUser;

public class CourseService {
    public Course makeCourse(Long id, String title, NsUser loginUser, int ordinal) {
        return new Course(id, title, loginUser.getId(), ordinal);
    }
}
