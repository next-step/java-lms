package nextstep.courses.service;

import nextstep.courses.domain.*;
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
        Session session = sessionRepository.findBySessionId(sessionId)
                                           .orElseThrow(NotFoundException::new);
        Attendees attendees = attendeeRepository.findAllBySessionId(sessionId);
        Attendee enrolledAttendee = session.enroll(payment, loginUser, attendees);
        attendeeRepository.save(enrolledAttendee);
    }
}
