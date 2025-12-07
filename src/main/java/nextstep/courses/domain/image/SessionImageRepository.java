package nextstep.courses.domain.image;

public interface SessionImageRepository {
    int save(SessionCoverImage image);

    SessionCoverImage findById(Long id);

    SessionCoverImage findBySessionId(Long sessionId);
}
