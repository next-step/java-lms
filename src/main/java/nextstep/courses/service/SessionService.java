package nextstep.courses.service;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.NsUserSession;
import nextstep.courses.domain.NsUserSessions;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.CoverImageDAO;
import nextstep.courses.infrastructure.SessionDAO;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.Teacher;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class SessionService {
    @Resource(name = "sessionDAO")
    private SessionDAO sessionDAO;

    @Resource(name = "sessionDAO")
    private CoverImageDAO coverImageDAO;

    public void save(Session session, List<CoverImage> coverImages) {
        sessionDAO.save(session);
        coverImageDAO.saveAll(coverImages);
    }

    public void enroll(Payment payment) throws CannotEnrollException {
        Long sessionId = payment.getSessionId();
        Session session = sessionDAO
                .findById(sessionId)
                .with(new NsUserSessions(sessionDAO.findNsUserSessionsBySessionId(sessionId)));

        sessionDAO.saveNsUserSession(session.enroll(payment));
    }
}
