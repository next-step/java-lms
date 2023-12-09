package nextstep.courses.domain;

public interface SessionUsersRepository {
    SessionUsers findBy(long sessionId);
    void addUserFor(SessionUser sessionUser);
    void save(SessionUser sessionUser);
}
