package nextstep.courses.domain.session.repository;

import nextstep.courses.domain.session.coverimage.CoverImages;

public interface CoverImageRepository {

    CoverImages findAllBySession(Long sessionId);
}
