package nextstep.courses.domain.repository;

public interface SessionImageRepository {
    int save(SessionImage sessionImage);

    int findBySessionId(Long sessionId);

    int findByImageId(Long imageId);
}
