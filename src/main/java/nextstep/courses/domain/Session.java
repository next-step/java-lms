package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public interface Session {

    boolean isSupport(SessionType sessionType);

    void apply(Payment payment);
}
