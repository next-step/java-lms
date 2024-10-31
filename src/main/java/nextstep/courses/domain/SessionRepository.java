package nextstep.courses.domain;

public interface SessionRepository {

    int save(PaidSession paidSession);

    PaidSession findPaidById(long sessionId);
    int save(FreeSession freeSession);

    FreeSession findFreeById(long sessionId);
}
