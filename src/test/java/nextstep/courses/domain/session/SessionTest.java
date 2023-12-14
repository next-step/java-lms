package nextstep.courses.domain.session;

import nextstep.courses.MaxStudentsNumberExceededException;
import nextstep.courses.NotEnrollmentPeriodException;
import nextstep.courses.PriceMismatchException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    @Test
    public void 모집중이_아닌_강의_수강신청() {
        assertThatThrownBy(() -> {
            new Session(SessionStatusTest.PREPARING_SCHEDULE).enroll(new Payment(NsUserTest.JAVAJIGI.getId(), 0L));
        }).isInstanceOf(NotEnrollmentPeriodException.class);

        assertThatThrownBy(() -> {
            new Session(SessionStatusTest.CLOSED_SCHEDULE).enroll(new Payment(NsUserTest.JAVAJIGI.getId(), 0L));
        }).isInstanceOf(NotEnrollmentPeriodException.class);
    }

    @Test
    public void 모집중인_강의_수강신청() {
        Assertions.assertThatCode(() -> {
            new Session(SessionStatusTest.ENROLLING_SCHEDULE).enroll(new Payment(NsUserTest.JAVAJIGI.getId(), 0L));
        }).doesNotThrowAnyException();
    }

    @Test
    public void 무료강의_수강신청() {
        Session session = new Session(true, new Schedule());
        Assertions.assertThatCode(() -> {
            session.enroll(new Payment(NsUserTest.JAVAJIGI.getId(), 0L));
            session.enroll(new Payment(NsUserTest.SANJIGI.getId(), 0L));
        }).doesNotThrowAnyException();
    }

    @Test
    public void 최대_수강인원_초과() {
        Session session = new Session(1L, 10_000L);
        assertThatThrownBy(() -> {
            session.enroll(new Payment(NsUserTest.JAVAJIGI.getId(), 10_000L));
            session.enroll(new Payment(NsUserTest.SANJIGI.getId(), 10_000L));
        }).isInstanceOf(MaxStudentsNumberExceededException.class);
    }

    @Test
    public void 결제금액_수강료_불일치() {
        Session session = new Session(1L, 10_000L);
        Payment payment = new Payment("p1", 1L, 1L, 5_000L);
        assertThatThrownBy(() -> {
            session.enroll(payment);
        }).isInstanceOf(PriceMismatchException.class);
    }
}
