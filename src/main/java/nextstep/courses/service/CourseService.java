package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;

import nextstep.users.domain.NsUser;

public class CourseService {
    private CourseRepository courseRepository;
    public void enroll(NsUser user, Long courseId, Long sessionId){
        Course course = courseRepository.findById(courseId);
        course.enroll(user, sessionId);
    }
}
