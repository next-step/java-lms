package nextstep.session.domain;

public interface SessionCoverImageRepository {

    SessionCoverImage findBySessionId(Long sessionId);

    int save(SessionCoverImage coverImage);

}
