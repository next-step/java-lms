package nextstep.courses.domain.session;

public interface StudentsRepository {
    int save(long sessionId, Students students);

    Students findBySessionId(Long sessionId);
}
