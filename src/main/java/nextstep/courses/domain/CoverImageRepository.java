package nextstep.courses.domain;

public interface CoverImageRepository {

    int save(CoverImage coverImage);

    CoverImage findById(Long id);

    int update(CoverImage coverImage);

    int delete(Long id);
}
