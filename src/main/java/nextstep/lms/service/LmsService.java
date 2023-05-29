package nextstep.lms.service;

import nextstep.lms.domain.Course;
import nextstep.lms.domain.Image;
import nextstep.lms.domain.Session;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Service("lmsService")
public class LmsService {
    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "sessionService")
    private SessionService sessionService;

    @Resource(name = "enrollmentService")
    private EnrollmentService enrollmentService;

    @Transactional
    public void registerSession(Long courseId, LocalDateTime from, LocalDateTime to, Image coverImage, boolean isFree, int maxEnrollmentCount) {
        Course course = courseService.findById(courseId).orElseThrow(NotFoundException::new);
        sessionService.save(Session.of(course, from, to, coverImage, isFree, maxEnrollmentCount));
    }

    @Transactional
    public void enrollSession(NsUser loginUser, Long sessionId) {
        Session session = sessionService.findById(sessionId).orElseThrow(NotFoundException::new);
        enrollmentService.save(session.enroll(loginUser, LocalDateTime.now()));
    }
}
