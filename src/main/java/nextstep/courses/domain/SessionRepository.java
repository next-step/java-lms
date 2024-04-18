package nextstep.courses.domain;

public interface SessionRepository {

    Session save(Session session);
    Session findById(Long id);

    void saveStudents(Session session);

    void updateStudentSelect(Long sessionId, SelectedStudents students);

    SessionStudent findAcceptedStudentsById(Long sessionId);
}
