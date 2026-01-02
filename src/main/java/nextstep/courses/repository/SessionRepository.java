package nextstep.courses.repository;


import nextstep.courses.domain.Session;

public interface SessionRepository {
    Session save(Session session);
    Session findById(long id);
}
