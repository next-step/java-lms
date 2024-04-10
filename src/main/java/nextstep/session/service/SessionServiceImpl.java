package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.CourseService;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionDto;
import nextstep.session.infrastructure.SessionRepository;
import nextstep.session.type.SessionStatusType;
import nextstep.users.infrastructure.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    public static final int FREE_PRICE = 0;
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "coverService")
    private CoverService coverService;

    @Resource(name = "courseService")
    private CourseService courseService;

    @Resource(name = "studentService")
    private StudentService studentService;

    @Resource(name = "userService")
    private UserService userService;

    @Override
    public long save(Session session) {
        return sessionRepository.save(session.toDto());
    }

    @Override
    public Session findById(long sessionId) {
        SessionDto sessionDto = sessionRepository.findById(sessionId);
        Cover foundCover = coverService.findById(sessionDto.getCoverId());
        Course foundCourse = courseService.findById(sessionDto.getCourseId());
        Students foundStudents = studentService.findBySessionId(sessionId);
        Tutor foundTutor = new Tutor(userService.findByUserId(sessionDto.getTutorId()));

        if (sessionDto.getPrice() == FREE_PRICE) {
            return new FreeSession(
                    sessionDto.getId(),
                    new Duration(sessionDto.getStartDate(), sessionDto.getEndDate()),
                    foundCover,
                    SessionStatus.of(SessionStatusType.valueOf(sessionDto.getSessionStatus())),
                    sessionDto.getSessionName(),
                    foundCourse,
                    foundTutor,
                    foundStudents,
                    new BaseEntity(sessionDto.isDeleted(), sessionDto.getCreatedAt(), sessionDto.getLastModifiedAt())
            );
        }

        return new PaidSession(
                sessionDto.getId(),
                new Duration(sessionDto.getStartDate(), sessionDto.getEndDate()),
                foundCover,
                SessionStatus.of(SessionStatusType.valueOf(sessionDto.getSessionStatus())),
                sessionDto.getSessionName(),
                foundCourse,
                sessionDto.getMaxCapacity(),
                sessionDto.getEnrolled(),
                sessionDto.getPrice(),
                foundTutor,
                foundStudents,
                new BaseEntity(sessionDto.isDeleted(), sessionDto.getCreatedAt(), sessionDto.getLastModifiedAt())
        );
    }
}
