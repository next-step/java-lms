package nextstep.courses.domain;

import nextstep.courses.domain.CoverImageInfo;

public interface CoverImageInfoRepository {
	Long saveAndGetId(CoverImageInfo coverImageInfo);

	CoverImageInfo findById(Long id);
}
