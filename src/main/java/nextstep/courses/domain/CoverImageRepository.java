package nextstep.courses.domain;

public interface CoverImageRepository {
    int save(CoverImage coverImage, Long sessionId);

    CoverImage findBySessionId(Long sessionId);
}
