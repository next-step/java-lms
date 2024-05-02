package nextstep.courses.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoverImageRepository {

    Optional<CoverImage> findById(Long coverImageId);

    int save(CoverImage coverImage);
}
