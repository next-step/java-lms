package nextstep.courses.domain;


public interface SessionRepository {
    long save(Session session);

    Session findById(Long sessionId);

    int updateSessionStatus(Session session);
}
