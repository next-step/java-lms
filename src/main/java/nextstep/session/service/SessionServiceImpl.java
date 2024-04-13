package nextstep.session.service;

import nextstep.common.domain.DeleteHistoryTargets;
import nextstep.common.service.DeleteHistoryService;
import nextstep.payments.domain.Payment;
import nextstep.session.domain.*;
import nextstep.session.dto.SessionUpdateBasicPropertiesVO;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service("sessionService")
public class SessionServiceImpl implements SessionService {

    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "deleteHistoryService")
    private DeleteHistoryService deleteHistoryService;

    @Override
    public long save(Session session) {
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

    @Override
    public Session apply(long sessionId, Payment payment, Student student) {
        Session targetSession = findById(sessionId);

        targetSession.apply(student, payment, LocalDateTime.now());
        sessionRepository.apply(sessionId, student);

        return findById(sessionId);
    }

    @Override
    public Session deleteStudent(long sessionId, Student student, NsUser requestUser) {
        sessionRepository.unapply(sessionId, student);
        deleteHistoryService.saveAll(List.of(student.delete(requestUser)));

        return findById(sessionId);
    }

    @Override
    public void delete(long sessionId, NsUser requestUser) {
        Session targetSession = findById(sessionId);
        DeleteHistoryTargets deleteHistoryTargets = targetSession.delete(requestUser);

        sessionRepository.delete(sessionId);
        deleteHistoryService.saveAll(deleteHistoryTargets.asList());
    }
}
