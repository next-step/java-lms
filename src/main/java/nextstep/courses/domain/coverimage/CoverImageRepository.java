package nextstep.courses.domain.coverimage;

public interface CoverImageRepository {

  int save(CoverImage coverImage);
  void saveAll(CoverImages coverImages);
  CoverImage findById(Long id);

}
