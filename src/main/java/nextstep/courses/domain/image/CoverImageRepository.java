package nextstep.courses.domain.image;

import java.util.List;

public interface CoverImageRepository {

    int save(CoverImage coverImage);

    List<CoverImage> findBySessionId(Long id);
}
