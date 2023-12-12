package nextstep.courses.domain.session;

import nextstep.courses.NotEnrollmentPeriodException;
import nextstep.courses.PriceMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    @Test
    public void 모집중이_아닌_강의_수강신청() {
        assertThatThrownBy(() -> {
            SessionStatusTest.PREPARING_SESSION.enroll(new Payment(NsUserTest.JAVAJIGI.getId()));
        }).isInstanceOf(NotEnrollmentPeriodException.class);

        assertThatThrownBy(() -> {
            SessionStatusTest.CLOSED_SESSION.enroll(new Payment(NsUserTest.JAVAJIGI.getId()));
        }).isInstanceOf(NotEnrollmentPeriodException.class);
    }

    @Test
    public void 모집중인_강의_수강신청() {
        Assertions.assertThatCode(() -> {
            SessionStatusTest.ENROLLING_SESSION.enroll(new Payment(NsUserTest.JAVAJIGI.getId()));
        }).doesNotThrowAnyException();
    }
}
