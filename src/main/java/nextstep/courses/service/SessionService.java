package nextstep.courses.service;

import nextstep.courses.domain.attendee.Attendee;
import nextstep.courses.domain.attendee.AttendeeRepository;
import nextstep.courses.domain.session.EnrollmentSession;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.payments.domain.Payment;
import nextstep.qna.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final AttendeeRepository attendeeRepository;

    public SessionService(SessionRepository sessionRepository, AttendeeRepository attendeeRepository) {
        this.sessionRepository = sessionRepository;
        this.attendeeRepository = attendeeRepository;
    }

    public void enroll(Payment payment, NsUser loginUser, Long sessionId) {
        EnrollmentSession session = sessionRepository.findBySessionId(sessionId)
                                                     .orElseThrow(NotFoundException::new);
        Attendee enrolledAttendee = session.enroll(payment.getAmount(), loginUser.getId());
        attendeeRepository.save(enrolledAttendee);
    }
}
