package nextstep.courses.domain.session;

public interface ImageRepository {
    int save(Image image);

    Image findById(Long id);
}
