package nextstep.courses.domain.fixture;

import nextstep.payments.domain.Payment;

import static nextstep.courses.domain.fixture.IdFixture.*;
import static nextstep.courses.domain.fixture.SessionFeeFixture.SESSION_FEE;

public class PaymentFixture {

    public static final String PAYMENT_ID = "1b9d6bcd-bbfd-4b2d-9b5d-ab8dfbbd4bed";

    public static Payment payment(Long amount) {
        return new Payment(PAYMENT_ID, SESSION_ID, NS_USER_ID, amount);
    }

    public static Payment payment() {
        return new Payment(PAYMENT_ID, SESSION_ID, NS_USER_ID, SESSION_FEE);
    }

}
