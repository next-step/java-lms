package nextstep.common.domain;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ImageRepository {
    Image save(Image image);
    Optional<Image> findByImageId(Long imageId);
    List<Image> findAll();
}
