package nextstep.courses.domain.session;

public interface CoverImageRepository {
    int save(CoverImage coverImage, Long sessionId);

    CoverImage findBySessionId(Long id);
}
