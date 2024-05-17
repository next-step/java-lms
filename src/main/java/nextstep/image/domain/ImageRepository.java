package nextstep.image.domain;

import nextstep.image.domain.Image;

import java.util.List;

public interface ImageRepository {

    int save(Image image);

    Image findById(Long id);

    List<Image> findBySessionId(Long id);

    void saveAll(List<Image> images);
}
