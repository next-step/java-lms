package nextstep.courses.service;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Optional;

@Service("coursesService")
public class CoursesService {

    @Resource
    private CourseRepository courseRepository;

    @Transactional
    public void enrolment(NsUser student, long courseId, long sessionId){
        findCourse(courseId)
                .enrolment(student, sessionId);
    }

    @Transactional
    public void startRecruiting(long courseId, long sessionId){
        findCourse(courseId)
                .startRecruiting(sessionId);
    }

    @Transactional
    public void startSession(long courseId, long sessionId){
        findCourse(courseId)
                .startSession(sessionId);
    }

    @Transactional
    public void endSession(long courseId, long sessionId){
        findCourse(courseId)
                .endSession(sessionId);
    }

    private Course findCourse(long courseId){
        return Optional.ofNullable(courseRepository.findById(courseId))
                .orElseThrow();
    }
}
