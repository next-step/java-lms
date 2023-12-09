package nextstep.courses.domain;

public interface SessionStudentsRepository {
    int save(Student student);

    SessionStudents findBySessionId(Long sessionId);
}
