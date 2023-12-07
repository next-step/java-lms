package nextstep.lms.service;

import nextstep.lms.domain.Session;
import nextstep.lms.repository.SessionRepository;
import nextstep.lms.repository.StudentsRepository;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("lmsService")
public class LmsService {
    @Resource(name = "sessionRepository")
    private SessionRepository sessionRepository;

    @Resource(name = "studentsRepository")
    private StudentsRepository studentsRepository;

    @Transactional
    public void enrollStudent(Payment payment) {
        Session session = sessionRepository.findById(payment.getSessionId());
        if (session.enroll(payment)) {
            studentsRepository.save(payment.getNsUserId(), payment.getSessionId());
        }
    }
}
