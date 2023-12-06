package nextstep.courses.domain.image;

public interface ImageRepository {
    int save(CoverImage coverImage);

    CoverImage findById(Long id);
}
