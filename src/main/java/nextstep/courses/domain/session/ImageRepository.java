package nextstep.courses.domain.session;

public interface ImageRepository {
    int save(Image image, long sessionId);

    Image findById(Long id);
}
