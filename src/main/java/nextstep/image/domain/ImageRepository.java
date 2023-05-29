package nextstep.image.domain;

import java.util.List;
import java.util.Optional;

public interface ImageRepository {
    Image save(Image image);

    Optional<Image> findByImageId(Long imageId);

    List<Image> findAll();
}
