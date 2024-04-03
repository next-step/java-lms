package nextstep.courses.domain;

import nextstep.courses.CannotEnrollSessionException;
import nextstep.courses.domain.vo.Capacity;
import nextstep.courses.domain.vo.CoverImage;
import nextstep.courses.domain.vo.ImageFile;
import nextstep.courses.domain.vo.ImageSize;
import nextstep.courses.domain.vo.Money;
import nextstep.courses.domain.vo.Period;
import nextstep.courses.domain.vo.Price;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserFixtures;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class SessionTest {


    @DisplayName("무료 강의는 수강신청 제한 인원이 없다")
    @Test
    void freeSessionEnrollTest() {
        Session sut = new FreeSession(
                new Period(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15)),
                new CoverImage(new ImageSize(300, 200), new ImageFile("test.png"), new Capacity(1)),
                SessionState.RECRUITING
        );

        Assertions.assertThatCode(() -> sut.enroll(NsUserFixtures.JAVAJIGI, new FreeStrategy())).doesNotThrowAnyException();

    }
    
    @DisplayName("무료 강의는 수강신청 제한 인원이 차지 않았다면 수강신청이 가능하다")
    @Test
    void paySessionEnrollTest() {

        PaySession sut = new PaySession(
                new Period(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15)),
                new CoverImage(new ImageSize(300, 200), new ImageFile("test.png"), new Capacity(1)),
                new Attendees(1),
                new Price(new Money(1000)),
                SessionState.RECRUITING
        );

        Assertions.assertThatCode(() -> sut.enroll(NsUserFixtures.JAVAJIGI, price -> true)).doesNotThrowAnyException();
    }

    @DisplayName("유료 강의는 수강신청 제한 인원이 차면 수강신청이 불가능하다")
    @Test
    void paySessionEnrollFailTest() {

        Session sut = new PaySession(
                new Period(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15)),
                new CoverImage(new ImageSize(300, 200), new ImageFile("test.png"), new Capacity(1)),
                new Attendees(1),
                new Price(new Money(1000)),
                SessionState.RECRUITING
        );

        Assertions.assertThatThrownBy(() -> {
            sut.enroll(NsUserFixtures.JAVAJIGI, price -> true);
            sut.enroll(NsUserFixtures.SANJIGI, price -> true);
        }).isInstanceOf(CannotEnrollSessionException.class);
    }

    @DisplayName("강의료와 지불 금액이 불일치하면 수강신청 불가능하다")
    @Test
    void paySessionEnrollWhenPaid() {

        Session sut = new PaySession(
                new Period(LocalDate.of(2024, 1, 1), LocalDate.of(2024, 1, 15)),
                new CoverImage(new ImageSize(300, 200), new ImageFile("test.png"), new Capacity(1)),
                new Attendees(10),
                new Price(new Money(1000)),
                SessionState.RECRUITING
        );

        Payment payment = new Payment(null, null, null, 10L);
        ChargeStrategy paidStrategy = new PaidStrategy(payment);

        Assertions.assertThatThrownBy(() -> {
            sut.enroll(NsUserFixtures.JAVAJIGI, paidStrategy);
        }).isInstanceOf(CannotEnrollSessionException.class);
    }



}