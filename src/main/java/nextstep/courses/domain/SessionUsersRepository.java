package nextstep.courses.domain;

public interface SessionUsersRepository {
    SessionUsers findBy(long sessionId);
}
