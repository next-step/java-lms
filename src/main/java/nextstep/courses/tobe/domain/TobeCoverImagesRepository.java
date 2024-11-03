package nextstep.courses.tobe.domain;

import nextstep.courses.tobe.domain.session.TobeCoverImages;

public interface TobeCoverImagesRepository {
    int saveAll(TobeCoverImages images);
    TobeCoverImages findAllBySessionId(long sessionId);
}
