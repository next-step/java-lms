package nextstep.courses.domain;

import nextstep.courses.domain.session.CoverImages;

public interface CoverImagesRepository {
    int saveAll(CoverImages images);
    CoverImages findAllBySessionId(long sessionId);
}
