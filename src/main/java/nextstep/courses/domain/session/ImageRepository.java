package nextstep.courses.domain.session;

public interface ImageRepository {
    int save(Image image, long sessionId);

    int save(Images images, long sessionId);

    Image findBySessionId(Long id);

    Images findImagesBySessionId(Long id);
}
