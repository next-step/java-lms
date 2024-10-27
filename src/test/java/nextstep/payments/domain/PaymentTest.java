package nextstep.payments.domain;

import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {

    private Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment("pay1", 1L, NsUserTest.JAVAJIGI, 10000L);
    }

    @Test
    void create() {
        Payment actual = new Payment("pay1", 1L, NsUserTest.JAVAJIGI, 10000L);
        Payment expected = new Payment("pay1", 1L, NsUserTest.JAVAJIGI, 10000L);

        Assertions.assertThat(actual).isEqualTo(expected);
    }

    @Test
    void matchAmount() {
        boolean actualFalse1 = payment.matchAmount(9999L);
        boolean actualFalse2 = payment.matchAmount(10001L);
        boolean actualTrue = payment.matchAmount(10000L);

        Assertions.assertThat(actualFalse1).isFalse();
        Assertions.assertThat(actualFalse2).isFalse();
        Assertions.assertThat(actualTrue).isTrue();
    }

    @Test
    void payingUser() {
        NsUser actual = payment.payingUser();

        Assertions.assertThat(actual).isEqualTo(NsUserTest.JAVAJIGI);
    }
}