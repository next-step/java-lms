package nextstep.courses.domain.session;

public interface SessionRepository {

    int save(SessionParticipant participant, SessionCondition condition, SessionTerm term, Long courseId);

    Session findById(Long id);
}
