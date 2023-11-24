package nextstep.courses.domain;

import nextstep.courses.domain.code.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;

public interface Session {

    boolean isSupport(SessionType sessionType);

    void apply(Payment payment,
               NsUser student);
}
