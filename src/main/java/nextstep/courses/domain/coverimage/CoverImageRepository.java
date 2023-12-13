package nextstep.courses.domain.coverimage;

public interface CoverImageRepository {

  int save(CoverImage coverImage);

  CoverImage findById(Long id);

}
