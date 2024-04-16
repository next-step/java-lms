package nextstep.courses.domain;

import java.util.List;

public interface CoverImageInfoRepository {
    Long saveAndGetId(CoverImageInfo coverImageInfo);

    CoverImageInfo findById(Long id);

    List<CoverImageInfo> findBySessionId(Long sessionId);
}
