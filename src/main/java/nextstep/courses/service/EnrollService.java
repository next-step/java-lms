package nextstep.courses.service;

import nextstep.courses.domain.*;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("enrollService")
public class EnrollService {

    @Resource(name = "courseRepository")
    private CourseRepository courseRepository;

    @Transactional
    public void enrollCourse(long courseId, long sessionId, Payment payment, Member member) {
        Session session = findSession(courseId, sessionId);
        session.enroll(payment, member); // 조건 만족해야만 Enrollment(PENDING) 생성
    }

    @Transactional
    public void approveEnrollment(long courseId, long sessionId, Member member) {
        Session session = findSession(courseId, sessionId);
        session.approveEnrollment(member);
    }

    @Transactional
    public void rejectEnrollment(long courseId, long sessionId, Member member) {
        Session session = findSession(courseId, sessionId);
        session.rejectEnrollment(member);
    }

    private Session findSession(long courseId, long sessionId) {
        Course course = courseRepository.findById(courseId);
        return course.getSessions().findById(sessionId);
    }
}

