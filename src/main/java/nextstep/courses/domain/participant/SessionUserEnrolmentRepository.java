package nextstep.courses.domain.participant;

import java.util.Optional;

public interface SessionUserEnrolmentRepository {

    int save(SessionUserEnrolment enrolment);

    int updateSubscriptionStatus(SessionUserEnrolment enrolment);

    int updateApprovalStatus(SessionUserEnrolment enrolment);

    Optional<SessionUserEnrolment> findBySessionIdAndUserId(Long findSessionId, Long userId);

    SessionParticipants findBySessionId(Long findSessionId);
}
