package nextstep.courses.domain.image;

public interface ImageRepository {

    int save(Image course);

    Image findById(Long id);

    Image findBySessionId(Long findSessionId);
}
