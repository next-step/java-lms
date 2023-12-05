package nextstep.courses.domain.cource;

public interface ImageRepository {
    int save(Image image);

    Image findById(Long id);
}
