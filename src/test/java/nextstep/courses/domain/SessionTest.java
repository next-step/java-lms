package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.courses.domain.vo.*;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserFixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Member;
import java.time.LocalDate;

class SessionTest {


    @DisplayName("무료 강의는 수강신청 제한 인원이 없다")
    @Test
    void freeSessionEnrollTest() {
        var sut = SessionFixture.create(
                AttendeesSlut.UNLIMIT,
                SessionState.RECRUITING,
                Price.FREE
        );

        var freeEnrollCommand = new FreeEnrollCommand(NsUserFixtures.JAVAJIGI);

        Assertions.assertThatCode(() -> {
            for (int i = 0; i < 10; i++) {
                sut.enroll(freeEnrollCommand);
            }
        }).doesNotThrowAnyException();

    }
    
    @DisplayName("유료 강의는 수강신청 제한 인원이 차지 않았다면 수강신청이 가능하다")
    @Test
    void paySessionEnrollTest() {

        var sut = SessionFixture.create(
                new AttendeesSlut(5),
                SessionState.RECRUITING,
                new Price(new Money(1000L))
        );

        var mockPayment = new Payment("id", 1L, 1L, 1000L);
        var paidEnrollCommand = new PaidEnrollCommand(NsUserFixtures.JAVAJIGI, mockPayment);

        Assertions.assertThatCode(() -> sut.enroll(paidEnrollCommand)).doesNotThrowAnyException();
    }

    @DisplayName("유료 강의는 수강신청 제한 인원이 차면 수강신청이 불가능하다")
    @Test
    void paySessionEnrollFailTest() {

        Session sut = SessionFixture.create(
                new AttendeesSlut(1),
                SessionState.RECRUITING,
                new Price(new Money(1000L))
        );

        var mockPayment = new Payment("id", 1L, 1L, 1000L);
        var paidEnrollCommand1 = new PaidEnrollCommand(NsUserFixtures.JAVAJIGI, mockPayment);
        var paidEnrollCommand2 = new PaidEnrollCommand(NsUserFixtures.SANJIGI, mockPayment);

        Assertions.assertThatThrownBy(() -> {
            sut.enroll(paidEnrollCommand1);
            sut.enroll(paidEnrollCommand2);
        }).isInstanceOf(CannotEnrollSessionException.class);
    }

    @DisplayName("강의료와 지불 금액이 불일치하면 수강신청 불가능하다")
    @Test
    void paySessionEnrollWhenPaid() {

        Session sut = SessionFixture.create(
                new AttendeesSlut(1),
                SessionState.RECRUITING,
                new Price(new Money(1000L))
        );

        Payment mockPayment = new Payment(null, null, null, 10L);
        var paidEnrollCommand = new PaidEnrollCommand(NsUserFixtures.JAVAJIGI, mockPayment);

        Assertions.assertThatThrownBy(() -> {
            sut.enroll(paidEnrollCommand);
        }).isInstanceOf(CannotEnrollSessionException.class);
    }





}