package nextstep.courses.domain;

public interface SessionRepository {
    int save(Session session, Long courseId);
    void saveImage(int sessionId, Image image);
    Session findById(Long id);
}
