package nextstep.courses.domain;

import nextstep.courses.tobe.domain.session.TobeCoverImages;

public interface CoverImagesRepository {
    int saveAll(TobeCoverImages images);
    TobeCoverImages findAllBySessionId(long sessionId);
}
