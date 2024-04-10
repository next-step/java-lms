package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.common.service.DeleteHistoryService;
import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.CourseService;
import nextstep.payments.domain.Payment;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionDto;
import nextstep.session.dto.SessionUpdateBasicPropertiesDto;
import nextstep.session.infrastructure.SessionRepository;
import nextstep.session.type.SessionStatusType;
import nextstep.users.domain.NsUser;
import nextstep.users.infrastructure.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

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

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

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

    @Override
    public int updateBasicProperties(long sessionId, SessionUpdateBasicPropertiesDto sessionUpdateDto) {
        SessionDto foundSessionDto = sessionRepository.findById(sessionId);
        return sessionRepository.updateSessionBasicProperties(foundSessionDto, sessionUpdateDto);
    }

    @Transactional
    @Override
    public int updateCover(long sessionId, long oldCoverId, Cover newCover, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        Cover beforeCover = coverService.findById(oldCoverId);

        coverService.delete(beforeCover, requestUser);
        long savedNewCoverId = coverService.save(newCover);
        Cover savedNewCover = coverService.findById(savedNewCoverId);

        return sessionRepository.updateCover(targetSession.toDto(), savedNewCover);
    }

    @Override
    public Session apply(long sessionId, Payment payment, Student student) {
        Session targetSession = findById(sessionId);
        Student studentWithSession = new Student(targetSession.toDto().getId(), student.getUser());

        studentService.save(studentWithSession);

        targetSession.apply(studentWithSession, payment, LocalDateTime.now());

        return findById(sessionId);
    }

    @Override
    public Session deleteStudent(long sessionId, Student student, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        Student studentWithSession = new Student(targetSession.toDto().getId(), student.getUser());

        DeleteHistory deleteHistory = studentService.delete(requestUser, studentWithSession);
        deleteHistoryService.saveAll(List.of(deleteHistory));

        return findById(sessionId);
    }

    @Override
    public void delete(long sessionId, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        DeleteHistory sessionDeleteHistory = targetSession.delete(requestUser);

        Cover targetCover = coverService.findById(targetSession.toDto().getCoverId());
        DeleteHistory coverDeleteHistory = coverService.delete(targetCover, requestUser);

        Students targetStudents = studentService.findBySessionId(sessionId);
        DeleteHistoryTargets studentDeleteHistory = studentService.deleteAll(targetStudents, requestUser);

        studentDeleteHistory.addFirst(sessionDeleteHistory);
        studentDeleteHistory.addFirst(coverDeleteHistory);

        deleteHistoryService.saveAll(studentDeleteHistory.asList());
    }
}
