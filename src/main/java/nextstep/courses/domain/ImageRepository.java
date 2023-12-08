package nextstep.courses.domain;

public interface ImageRepository {
    Long save(Image image);

    Image findById(Long id);
}
