package nextstep.session.service;

import nextstep.common.domain.DeleteHistory;
import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.common.service.DeleteHistoryService;
import nextstep.payments.domain.Payment;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "coverService")
    private CoverService coverService;

    @Resource(name = "studentService")
    private StudentService studentService;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Override
    public long save(Session session) {
        coverService.save(session.getCover());
        return sessionRepository.save(session);
    }

    @Override
    public Session findById(long sessionId) {
        return sessionRepository.findById(sessionId);
    }

    @Override
    public int updateBasicProperties(long sessionId, SessionUpdateBasicPropertiesVO sessionUpdateDto) {
        return sessionRepository.updateSessionBasicProperties(sessionId, sessionUpdateDto);
    }

    @Transactional
    @Override
    public long updateCover(long sessionId, long oldCoverId, Cover newCover, NsUser requestUser) {
        Cover beforeCover = coverService.findById(oldCoverId);

        coverService.delete(beforeCover.getId(), requestUser);
        return coverService.save(newCover);
    }

    @Override
    public Session apply(long sessionId, Payment payment, Student student) {
        Session targetSession = findById(sessionId);
        Student studentWithSession = new Student(targetSession.toVO().getId(), student.getUserId());

        studentService.save(studentWithSession);

        targetSession.apply(studentWithSession, payment, LocalDateTime.now());

        return findById(sessionId);
    }

    @Override
    public Session deleteStudent(long sessionId, Student student, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        Student studentWithSession = new Student(targetSession.toVO().getId(), student.getUserId());

        DeleteHistory deleteHistory = studentService.delete(requestUser, studentWithSession);
        deleteHistoryService.saveAll(List.of(deleteHistory));

        return findById(sessionId);
    }

    @Override
    public void delete(long sessionId, NsUser requestUser) {
        DeleteHistoryTargets deleteHistoryTargets = new DeleteHistoryTargets();
        Session targetSession = findById(sessionId);
        deleteHistoryTargets.addFirst(targetSession.delete(requestUser));

        deleteHistoryTargets.addFirst(coverService.delete(targetSession.toVO().getCoverId(), requestUser));

        Students targetStudents = studentService.findBySessionId(sessionId);
        deleteHistoryTargets.add(studentService.deleteAll(targetStudents, requestUser));

        deleteHistoryService.saveAll(deleteHistoryTargets.asList());
    }
}
