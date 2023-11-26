package nextstep.courses.domain;

public interface SessionRepository {
    Session findById(long sessionId);
}
