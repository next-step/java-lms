package nextstep.courses.domain.image;

public interface CoverImageRepository {

    int save(CoverImage coverImage);

    CoverImage findById(Long id);
}
