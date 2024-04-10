package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

import java.time.LocalDateTime;
import java.util.List;

public class FreeSession extends Session {

    private static final int MAXIMUM_NUMBER = Integer.MAX_VALUE;

    public FreeSession(long id, LocalDateTime startedAt, LocalDateTime endedAt) {
        super(id, MAXIMUM_NUMBER, startedAt, endedAt);
    }
}
