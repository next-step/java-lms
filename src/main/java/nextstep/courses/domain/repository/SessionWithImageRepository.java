package nextstep.courses.domain.repository;

public interface SessionWithImageRepository {
    int save(Long sessionId, Long imageId);

    int findBySessionId(Long sessionId);

    int findByImageId(Long imageId);
}
