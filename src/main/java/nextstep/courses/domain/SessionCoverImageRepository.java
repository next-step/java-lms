package nextstep.courses.domain;

public interface SessionCoverImageRepository {

    int save(SessionCoverImage coverImage);

    SessionCoverImage findById(Long id);
}
