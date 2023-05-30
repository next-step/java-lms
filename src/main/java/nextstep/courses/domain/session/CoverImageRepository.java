package nextstep.courses.domain.session;

public interface CoverImageRepository {

    int save(CoverImage coverImage);

    CoverImage findById(Long id);

    int update(CoverImage coverImage);

    int delete(CoverImage image);

}
