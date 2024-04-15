package nextstep.courses.domain;

import nextstep.courses.domain.CoverImageInfo;

public interface CoverImageInfoRepository {
	int save(CoverImageInfo coverImageInfo);

	CoverImageInfo findById(Long id);
}
