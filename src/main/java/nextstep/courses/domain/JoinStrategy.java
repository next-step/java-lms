package nextstep.courses.domain;

import nextstep.payments.domain.Payment;

public interface JoinStrategy {
    boolean joinable(Session session, Payment pay);
}
