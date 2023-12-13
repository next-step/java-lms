package nextstep.courses.service;

import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.*;
import nextstep.courses.infrastructure.CoverImageDAO;
import nextstep.courses.infrastructure.SessionDAO;
import nextstep.payments.domain.Payment;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class SessionService {
    @Resource(name = "sessionDAO")
    private SessionDAO sessionDAO;

    @Resource(name = "sessionDAO")
    private CoverImageDAO coverImageDAO;

    public void save(Session session, CoverImage coverImage) {
        sessionDAO.save(session);
        coverImageDAO.save(coverImage);
    }

    public void enroll(Payment payment) throws CannotEnrollException {
        List<NsUserSession> nsUserSessions = sessionDAO.getNsUserSessions(payment.getSessionId());
        Session session = sessionDAO.findById(payment.getSessionId());
        Enrollment enrollment = new Enrollment(session, new NsUserSessions(nsUserSessions));
        NsUserSession nsUserSession = enrollment.enroll(payment);
//        NsUserSession nsUserSession = session.enroll(payment, nsUserSessions);

        sessionDAO.saveNsUserSession(nsUserSession);
    }
}
