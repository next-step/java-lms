package nextstep.courses.domain;

public interface SessionImageRepository {
    SessionImages findBy(Long sessionId);
}
