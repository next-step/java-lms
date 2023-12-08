package nextstep.sessions.repository;

import java.util.List;

import nextstep.sessions.domain.data.vo.CoverImage;

public interface CoverImageRepository {

    int saveAll(int sessionId, List<CoverImage> coverImages);

    List<CoverImage> findById(int sessionId);

}
