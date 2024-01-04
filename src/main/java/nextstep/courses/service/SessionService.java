package nextstep.courses.service;

import nextstep.courses.CannotApproveException;
import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.Session;
import nextstep.courses.infrastructure.CoverImageDAO;
import nextstep.courses.infrastructure.SessionDAO;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Transactional
public class SessionService {
    @Resource(name = "sessionDAO")
    private SessionDAO sessionDAO;

    @Resource(name = "sessionDAO")
    private CoverImageDAO coverImageDAO;

    @Resource(name = "userRepository")
    private UserRepository userRepository;

    public void save(Session session, List<CoverImage> coverImages) {
        sessionDAO.save(session);
        coverImageDAO.saveAll(coverImages);
    }

    public void enroll(Payment payment) throws CannotEnrollException {
        Long sessionId = payment.getSessionId();
        Session session = sessionDAO
                .findById(sessionId)
                .with(new Students(sessionDAO.findStudnetsBySessionId(sessionId)));

        sessionDAO.saveStudent(session.enroll(payment));
    }

    public void changeEnrollmentStatus(NsUser teacher, Student nsUserSession) throws CannotApproveException {
        // 수강 신청을 승인 혹은 취소한 강사 정보 및 신청 정보 값이 들어온다.
        Session session = sessionDAO
                .findById(nsUserSession.sessionId())
                .with(new Students(sessionDAO.findStudnetsBySessionId(nsUserSession.sessionId())));
        session.canChangeEnrollmentStatus(teacher, nsUserSession);

        sessionDAO.updateStudent(nsUserSession);
    }
}
