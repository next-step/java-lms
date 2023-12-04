package nextstep.courses.domain.repository;

public class SessionImage {
    private final Long id;
    private final Long sessionId;
    private final Long imageId;

    public SessionImage(Long id, Long sessionId, Long imageId) {
        this.id = id;
        this.sessionId = sessionId;
        this.imageId = imageId;
    }
}
