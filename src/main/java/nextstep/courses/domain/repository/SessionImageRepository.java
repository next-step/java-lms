package nextstep.courses.domain.repository;

public interface SessionImageRepository {
    int save(Long sessionId, Long imageId);

    int findBySessionId(Long sessionId);

    int findByImageId(Long imageId);
}
