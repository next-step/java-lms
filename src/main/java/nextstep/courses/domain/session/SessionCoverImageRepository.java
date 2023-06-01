package nextstep.courses.domain.session;

public interface SessionCoverImageRepository {

    int save(SessionCoverImage sessionCoverImage, Long sessionId);

    SessionCoverImage findById(Long id);
}
