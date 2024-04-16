package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.image.Image;

public interface ImageRepository {
    int save(Image image);
}
