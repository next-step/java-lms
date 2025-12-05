package nextstep.courses.service;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import nextstep.courses.domain.session.Sessions;
import nextstep.payments.domain.Payment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SessionService {
    private final SessionRepository sessionRepository;

    public SessionService(SessionRepository sessionRepository) {
        this.sessionRepository = sessionRepository;
    }

    //TODO 추후 메서드의 파라미터는 사용하기에 따라 정리한다(현재 인수 4개)
    public void enroll(Long courseId, int cohort, Long nsUserId, Payment payment) {
        Sessions sessions = sessionRepository.findByCourseId(courseId);
        Session session = sessions.findByCohort(cohort);

        if (session == null) {
            throw new IllegalArgumentException("해당 기수의 강의를 찾을 수 없습니다.");
        }

        session.enroll(nsUserId, payment);

        Long sessionId = sessionRepository.findSessionIdByCourseIdAndCohort(courseId, cohort);
        sessionRepository.saveEnrollment(sessionId, nsUserId);
    }
}
