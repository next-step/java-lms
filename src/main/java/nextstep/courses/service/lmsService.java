package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.dto.RequestSessionDto;
import org.springframework.stereotype.Service;

@Service
public class lmsService {

    public boolean registerSession(RequestSessionDto requestSessionDto) {
        Course course = new Course(requestSessionDto.getCourseTitle(), 1L);
        return course.registerSession(new SessionName(requestSessionDto.getSessionName()),
            requestSessionDto.getPayment());
    }
}
