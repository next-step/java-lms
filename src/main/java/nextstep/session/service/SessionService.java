package nextstep.session.service;

import nextstep.courses.domain.Course;
import nextstep.courses.service.CourseService;
import nextstep.session.domain.Session;
import nextstep.session.ui.CreateSessionDto;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionService {

    @Resource(name = "courseService")
    private CourseService courseService;


    public Session createSession(NsUser loginUser, CreateSessionDto dto) {
        Course course = courseService.findCourseById(dto.getCourseId());
        Session session = dto.toSession(loginUser);
        course.addSession(session);
        return session;
    }
}
