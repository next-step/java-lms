package nextstep.sessions.repository;

import nextstep.sessions.domain.data.vo.CoverImage;

public interface CoverImageRepository {

    int saveCoverImage(int sessionId, CoverImage coverImage);

    CoverImage findCoverImageBySessionId(int sessionId);
    
}
