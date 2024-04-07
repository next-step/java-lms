package nextstep.courses.domain;

public interface CoverImageInfoRepository {
    int save(CoverImageInfo coverImageInfo);

    CoverImageInfo findById(Long id);
}
