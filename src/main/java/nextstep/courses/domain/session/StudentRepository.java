package nextstep.courses.domain.session;

public interface StudentRepository {
    int save(Student student);

    Student findBySessionId(Long sessionId);
}
