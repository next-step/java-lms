package nextstep.courses.domain;

public interface SessionRepository {
    Long saveSessionAndGetId(Session session);

    Session findById(Long id);
}
