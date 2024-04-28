package nextstep.sessions.domain;

import nextstep.sessions.domain.image.Image;

import java.util.Optional;

public interface ImageRepository {
    int save(Image image);

    Optional<Image> findById(long imageId);
}
