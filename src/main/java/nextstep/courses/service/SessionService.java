package nextstep.courses.service;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.coverImage.CoverImage;
import nextstep.courses.domain.coverImage.CoverImageRepository;
import nextstep.courses.domain.session.Period;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.students.Students;
import nextstep.courses.domain.students.StudentsRepository;
import nextstep.courses.dto.CoverImageDto;
import nextstep.users.domain.NsUser;

public class SessionService {

    private final SessionRepository sessionRepository;
    private final CourseRepository courseRepository;
    private final CoverImageRepository coverImageRepository;
    private final StudentsRepository studentsRepository;

    public SessionService(SessionRepository sessionRepository, CourseRepository courseRepository, CoverImageRepository coverImageRepository, StudentsRepository studentsRepository) {
        this.sessionRepository = sessionRepository;
        this.courseRepository = courseRepository;
        this.coverImageRepository = coverImageRepository;
        this.studentsRepository = studentsRepository;
    }

    // 무료 세션 생성
    public void createFreeSession(Long courseId, CoverImageDto coverImageDto, Period period) {
        Course course = courseRepository.findById(courseId);
        CoverImage savedCoverImage = saveCoverImage(coverImageDto);
        Session session = Session.freeSession(savedCoverImage, period);
        course.addSession(session);
        sessionRepository.save(session);
    }

    // 유료 세션 생성
    public void createNotFreeSession(Long courseId, int maxAttendance, CoverImageDto coverImageDto, Period period) {
        Course course = courseRepository.findById(courseId);
        CoverImage savedCoverImage = saveCoverImage(coverImageDto);
        Session session = Session.notFreeSession(savedCoverImage, maxAttendance, period);
        course.addSession(session);
        sessionRepository.save(session);
    }

    // 세션 참여자 등록
    public void attendSession(NsUser nsUser, Long sessionId) {
        Session session = sessionRepository.findById(sessionId);
        Students students = studentsRepository.findBySession(session);
        session.bindStudents(students);
        session.addStudent(nsUser);
        studentsRepository.save(session, nsUser);
    }

    private CoverImage saveCoverImage(CoverImageDto coverImageDto) {
        CoverImage coverImage = coverImageDto.toCoverImage();
        Long coverImageId = coverImageRepository.save(coverImage);
        return coverImage.toSavedCoverImage(coverImageId);
    }
}
