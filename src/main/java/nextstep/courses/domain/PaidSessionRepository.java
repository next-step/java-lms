package nextstep.courses.domain;

public interface PaidSessionRepository {

    int save(PaidSession paidSession);

    PaidSession findById(long sessionId);
}
