package nextstep.sessions.infrastructore;

import nextstep.sessions.domain.CoverImage;

import java.util.List;

public interface CoverImageRepository {
    long save(CoverImage coverImage);

    CoverImage findById(Long id);

    List<CoverImage> findBySessionId(Long sessionId);

    void saveAll(List<CoverImage> coverImages);
}
