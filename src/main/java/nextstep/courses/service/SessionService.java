package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.dto.CoverImageDto;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    public SessionService(SessionRepository sessionRepository, CourseRepository courseRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
    }

    // 무료 세션 생성
    public void createFreeSession(Long courseId, CoverImageDto coverImageDto, Period period) {
        Course course = courseRepository.findById(courseId);
        CoverImage coverImage = coverImageDto.toCoverImage();
        Session session = Session.freeSession(coverImage, course, period);
        sessionRepository.save(session);
    }

    // 유료 세션 생성
    public void createNotFreeSession(Long courseId, int maxAttendance, CoverImageDto coverImageDto, Period period) {
        Course course = courseRepository.findById(courseId);
        CoverImage coverImage = coverImageDto.toCoverImage();
        Session session = Session.notFreeSession(coverImage, maxAttendance, course, period);
        sessionRepository.save(session);
    }

    // 세션 참여자 등록
    public void attendSession(NsUser nsUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        session.addUser(nsUser);
        sessionRepository.enrollNsUser(session, nsUser);
    }
}
