package nextstep.courses.domain;

public interface StudentRepository {
    int save(Student student);

    Students findBySessionId(long sessionId);
}
