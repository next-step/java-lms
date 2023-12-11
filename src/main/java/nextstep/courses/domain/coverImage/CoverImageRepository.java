package nextstep.courses.domain.coverImage;

public interface CoverImageRepository {

    void save(CoverImage coverImage);

    CoverImages findBySessionId(Long sessionId);
}
