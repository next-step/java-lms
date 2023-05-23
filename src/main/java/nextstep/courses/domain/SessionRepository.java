package nextstep.courses.domain;

public interface SessionRepository {

    Session findByTitle(String title);

    void save(Session session);
}
