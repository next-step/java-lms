package nextstep.courses.domain;

public interface SessionUsersRepository {
    SessionUsers findBy(long sessionId);
    void addUserFor(long sessionId, long userId);
    void updateSessionUserStatus(long sessionId, long userId, SessionUserStatus sessionUserStatus);
}
