package nextstep.courses.Service;

import nextstep.courses.domain.Course;
import nextstep.sessions.domain.Session;
import nextstep.users.domain.NsUser;

public class CourseService {

    public void createCourse(NsUser loginUser, String courseName) {
        Course course = new Course(courseName, loginUser.getId());
    }

    public void addSession(NsUser loginUser, Session session) {

    }

    public void pay(NsUser loginUser) {

    }
}
