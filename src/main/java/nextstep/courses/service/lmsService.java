package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.session.SessionName;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class lmsService {

    public boolean registerSession(String courseTitle, String sessionName, Payment payment){
        Course course = new Course(courseTitle, 1L);
        return course.registerSession(new SessionName(sessionName), payment);
    }
}
