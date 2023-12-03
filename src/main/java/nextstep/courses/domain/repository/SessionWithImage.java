package nextstep.courses.domain.repository;

public class SessionWithImage {
    private final Long id;
    private final Long sessionId;
    private final Long imageId;

    public SessionWithImage(Long id, Long sessionId, Long imageId) {
        this.id = id;
        this.sessionId = sessionId;
        this.imageId = imageId;
    }
}
