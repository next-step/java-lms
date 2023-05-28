package nextstep.courses.domain;

public interface ImageRepository {
    Image findById(Long id);

    int save(Image image);
}
