package nextstep.courses.domain;

public interface FreeSessionRepository {
    int save(FreeSession freeSession);

    FreeSession findById(long sessionId);
}
