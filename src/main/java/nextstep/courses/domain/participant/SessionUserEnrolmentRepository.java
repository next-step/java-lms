package nextstep.courses.domain.session;

public interface SessionUserEnrolmentRepository {

    int save(SessionUserEnrolment enrolment);

    int update(SessionUserEnrolment enrolment);

    SessionUserEnrolment findBySessionIdAndUserId(Long findSessionId, Long userId);
}
