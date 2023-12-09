package nextstep.sessions.repository;

import java.util.List;

import nextstep.sessions.domain.data.coverimage.CoverImage;

public interface CoverImageRepository {

    int saveAll(int sessionId, List<CoverImage> coverImages);

    List<CoverImage> findAllBySessionId(int sessionId);

}
