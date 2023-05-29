package nextstep.lms.domain;

public interface StudentRepository {
    int save(Student student);

    Student findByNsUserIdAndSessionId(Long nsUserId, Long sessionId);

    void sessionCancel(Student student);

}
