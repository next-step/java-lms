package nextstep.sessions.repository;

import java.util.List;

import nextstep.sessions.domain.data.vo.CoverImage;

public interface CoverImageRepository {

    int saveCoverImage(int sessionId, CoverImage coverImage);

    int saveCoverImages(int sessionId, List<CoverImage> coverImages);

    CoverImage findCoverImageBySessionId(int sessionId);

    List<CoverImage> findCoverImagesBySessionId(int sessionId);

}
