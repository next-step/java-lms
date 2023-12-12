package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.sessionuser.*;
import nextstep.courses.dto.CoverImageDto;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final CourseRepository courseRepository;
    private final CoverImageRepository coverImageRepository;
    private final UserRepository userRepository;
    private final SessionUserRepository sessionUserRepository;

    public SessionService(SessionRepository sessionRepository, CourseRepository courseRepository, CoverImageRepository coverImageRepository, UserRepository userRepository, SessionUserRepository sessionUserRepository) {
        this.sessionRepository = sessionRepository;
        this.courseRepository = courseRepository;
        this.coverImageRepository = coverImageRepository;
        this.userRepository = userRepository;
        this.sessionUserRepository = sessionUserRepository;
    }

    // 무료 세션 생성
    public void createFreeSession(Long courseId, CoverImageDto coverImageDto, Period period) {
        Course course = courseRepository.findById(courseId);
        Session session = Session.freeSession(null, period);
        course.addSession(session);
        Long sessionId = sessionRepository.save(session);
        CoverImage coverImage = coverImageDto.toCoverImage(sessionId);
        coverImageRepository.save(coverImage);
    }

    // 유료 세션 생성
    public void createNotFreeSession(Long courseId, int maxAttendance, CoverImageDto coverImageDto, Period period) {
        Course course = courseRepository.findById(courseId);
        Session session = Session.notFreeSession(null, maxAttendance, period);
        course.addSession(session);
        Long sessionId = sessionRepository.save(session);
        CoverImage coverImage = coverImageDto.toCoverImage(sessionId);
        coverImageRepository.save(coverImage);
    }

    // 세션 참여자 등록
    public void attendSessionUser(String userId, Long sessionId, UserType userType) {
        NsUser nsUser = userRepository.findByUserId(userId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 사용자 입니다."));
        Session session = sessionRepository.findById(sessionId);
        SessionUser sessionUser = new SessionUser(nsUser.getId(), sessionId, userType);
        session.addSessionUser(sessionUser);
        sessionUserRepository.save(sessionUser);
    }

    // 학생 수강 취소
    public void cancelStudent(Long tutorId, Long studentId, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        SessionUsers sessionUsers = sessionUserRepository.findBySession(session);
        SessionUser tutor = sessionUsers.findSessionUser(tutorId);
        SessionUser student = sessionUsers.findSessionUser(studentId);
        tutor.cancel(student);
        sessionUserRepository.cancel(student);
    }
}
