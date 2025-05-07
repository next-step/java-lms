package nextstep.courses.domain.session.metadata.coverImage;

public interface CoverImageRepository {
    int save(CoverImage coverImage);
    CoverImage findById(Long id);
}
