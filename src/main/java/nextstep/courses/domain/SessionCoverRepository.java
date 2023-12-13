package nextstep.courses.domain;

public interface SessionCoverRepository {
    int save(SessionCover sessionCover);

    SessionCover findById(Long id);
}
