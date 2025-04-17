package nextstep.images.domain;

public interface ImageRepository {
    int save(Image image);

    Image findById(Long id);
}
