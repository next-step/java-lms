package nextstep.courses.domain;

public interface CoverImageInfoRepository {
    Long saveAndGetId(CoverImageInfo coverImageInfo);

    CoverImageInfo findById(Long id);
}
