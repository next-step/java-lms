package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.lecture.LectureName;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;

@Service
public class lmsService {

    public boolean registerLecture(String courseTitle, String lectureName, Payment payment){
        Course course = new Course(courseTitle, 1L);
        return course.registerLecture(new LectureName(lectureName), payment);
    }
}
