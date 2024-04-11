package nextstep.session.service;

import nextstep.common.domain.BaseEntity;
import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.common.service.DeleteHistoryService;
import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.CourseService;
import nextstep.payments.domain.Payment;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionVO;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;
import nextstep.session.domain.SessionRepository;
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
        return sessionRepository.save(session);
    }

    @Override
    public Session findById(long sessionId) {
        SessionVO sessionVO = sessionRepository.findById(sessionId);
        Cover foundCover = coverService.findById(sessionVO.getCoverId());
        Course foundCourse = courseService.findById(sessionVO.getCourseId());
        Students foundStudents = studentService.findBySessionId(sessionId);
        Tutor foundTutor = new Tutor(userService.findByUserId(sessionVO.getTutorId()));

        if (sessionVO.getPrice() == FREE_PRICE) {
            return new FreeSession(
                    sessionVO.getId(),
                    new Duration(sessionVO.getStartDate(), sessionVO.getEndDate()),
                    foundCover,
                    SessionStatus.of(SessionStatusType.valueOf(sessionVO.getSessionStatus())),
                    sessionVO.getSessionName(),
                    foundCourse,
                    foundTutor,
                    foundStudents,
                    new BaseEntity(sessionVO.isDeleted(), sessionVO.getCreatedAt(), sessionVO.getLastModifiedAt())
            );
        }

        return new PaidSession(
                sessionVO.getId(),
                new Duration(sessionVO.getStartDate(), sessionVO.getEndDate()),
                foundCover,
                SessionStatus.of(SessionStatusType.valueOf(sessionVO.getSessionStatus())),
                sessionVO.getSessionName(),
                foundCourse,
                sessionVO.getMaxCapacity(),
                sessionVO.getEnrolled(),
                sessionVO.getPrice(),
                foundTutor,
                foundStudents,
                new BaseEntity(sessionVO.isDeleted(), sessionVO.getCreatedAt(), sessionVO.getLastModifiedAt())
        );
    }

    @Override
    public int updateBasicProperties(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateDto) {
        return sessionRepository.updateSessionBasicProperties(sessionId, sessionUpdateDto);
    }

    @Transactional
    @Override
    public int updateCover(long sessionId, long oldCoverId, Cover newCover, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        Cover beforeCover = coverService.findById(oldCoverId);

        coverService.delete(beforeCover, requestUser);
        long savedNewCoverId = coverService.save(newCover);
        Cover savedNewCover = coverService.findById(savedNewCoverId);

        return sessionRepository.updateCover(targetSession.toVO().getId(), savedNewCover);
    }

    @Override
    public Session apply(long sessionId, Payment payment, Student student) {
        Session targetSession = findById(sessionId);
        Student studentWithSession = new Student(targetSession.toVO().getId(), student.getUser());

        studentService.save(studentWithSession);

        targetSession.apply(studentWithSession, payment, LocalDateTime.now());

        return findById(sessionId);
    }

    @Override
    public Session deleteStudent(long sessionId, Student student, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        Student studentWithSession = new Student(targetSession.toVO().getId(), student.getUser());

        DeleteHistory deleteHistory = studentService.delete(requestUser, studentWithSession);
        deleteHistoryService.saveAll(List.of(deleteHistory));

        return findById(sessionId);
    }

    @Override
    public void delete(long sessionId, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        DeleteHistory sessionDeleteHistory = targetSession.delete(requestUser);

        Cover targetCover = coverService.findById(targetSession.toVO().getCoverId());
        DeleteHistory coverDeleteHistory = coverService.delete(targetCover, requestUser);

        Students targetStudents = studentService.findBySessionId(sessionId);
        DeleteHistoryTargets studentDeleteHistory = studentService.deleteAll(targetStudents, requestUser);

        studentDeleteHistory.addFirst(sessionDeleteHistory);
        studentDeleteHistory.addFirst(coverDeleteHistory);

        deleteHistoryService.saveAll(studentDeleteHistory.asList());
    }
}
