package nextstep.courses.service;

import nextstep.courses.infrastructure.dto.LearnerDto;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.Session;
import nextstep.courses.infrastructure.JdbcLearnerRepository;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;

@Service
public class SessionService {

    private final JdbcLearnerRepository learnerRepository;

    public SessionService(JdbcLearnerRepository learnerRepository) {
        this.learnerRepository = learnerRepository;
    }

    public void joinSession(NsUser loginUser, Session session) {
        LearnerDto learnerDto = session.join(loginUser);
        learnerRepository.save(learnerDto);
    }

    public void joinPaidSession(NsUser loginUser, PaidSession paidSession, Payment payment) {
        LearnerDto learnerDto = paidSession.join(loginUser, payment);
        learnerRepository.save(learnerDto);
    }
}
