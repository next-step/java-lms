package nextstep.courses.domain.session;

public interface StudentsRepository {
    int save(long sessionId, Student student);

    int saveAll(long sessionId, Students students);

    int updateState(long sessionId, Student student);

    Students findBySessionId(Long sessionId);
}
