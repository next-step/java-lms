package nextstep.courses.tobe.domain;

import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.PaidSession;

public interface TobeSessionRepository {

    int save(TobePaidSession paidSession);

    TobePaidSession findPaidById(long sessionId);
    int save(TobeFreeSession freeSession);

    TobeFreeSession findFreeById(long sessionId);
}
