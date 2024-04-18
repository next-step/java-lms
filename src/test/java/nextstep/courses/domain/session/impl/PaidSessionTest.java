package nextstep.courses.domain.session.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.session.MaxRegistrationCount;
import nextstep.courses.domain.session.RegistrationCount;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.error.exception.MaxRegistrationCountNotZero;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

class PaidSessionTest {

    @Test
    void 강의_최대_수강_인원은_0일_수_없다() {
        assertThatThrownBy(() -> createSessionWithZeroMaxRegistrationCount())
            .isInstanceOf(MaxRegistrationCountNotZero.class)
            .hasMessage("최대 등록수는 0일수 없습니다 입력값: 0");
    }

    @ParameterizedTest
    @MethodSource("createSuccessPaidSession")
    void 강의_최대_수강_인원을_넘지_않을_경우_강의_신청이_가능해야한다(Session paidSession) {
        assertThat(paidSession.isRegistrationAvailable()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("createSessionWithExceededMaxRegistrationCount")
    void 강의_최대_수강_인원을_넘을_경우_강의_신청이_불가능해야한다(Session paidSession) {
        assertThat(paidSession.isRegistrationAvailable()).isFalse();
    }

    @ParameterizedTest
    @MethodSource("createSuccessPaidSession")
    void 유료강의는_수강생이_결제한_금액과_수강료가_일치하는_경우_강의_신청이_가능하다(Session paidSession) {
        assertThat(paidSession.isPaymentAmountSameTuitionFee(
            new Payment("1", 1L, 1L, new Money(1000)))).isTrue();
    }

    @ParameterizedTest
    @MethodSource("createSuccessPaidSession")
    void 유료강의는_수강생이_결제한_금액과_수강료가_일치하지_않은_경우_강의_신청이_불가능하다(Session paidSession) {
        assertThat(paidSession.isPaymentAmountSameTuitionFee(
            new Payment("1", 1L, 1L, new Money(500)))).isFalse();
    }

    @ParameterizedTest
    @MethodSource("createSuccessPaidSession")
    void 유료_강의_수강신청시_강의_상태가_모집중이라면_수강_신청이_가능해야한다(Session paidSession) {
        assertThat(paidSession.isRegistrationAvailable()).isTrue();
    }

    @ParameterizedTest
    @MethodSource("createSessionStatusPreparingSession")
    void 유료_강의_수강신청시_강의_상태가_모집중이_아니라면_수강_신청이_불가능해야한다(Session paidSession) {
        assertThat(paidSession.isRegistrationAvailable()).isFalse();
    }

    private PaidSession createSessionWithZeroMaxRegistrationCount() {
        return new PaidSession(
            new SessionName("강의이름"),
            new RegistrationCount(2),
            new MaxRegistrationCount(new RegistrationCount(0)),
            new Money(1000),
            new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(300), new ImageHeight(200)),
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionStatus.RECRUITING);
    }

    static Stream<PaidSession> createSessionWithExceededMaxRegistrationCount() {
        return Stream.of(new PaidSession(
            new SessionName("강의이름"),
            new RegistrationCount(4),
            new MaxRegistrationCount(new RegistrationCount(3)),
            new Money(1000),
            new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(300), new ImageHeight(200)),
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionStatus.RECRUITING));
    }

    static Stream<PaidSession> createSuccessPaidSession() {
        return Stream.of(new PaidSession(
            new SessionName("강의이름"),
            new RegistrationCount(2),
            new MaxRegistrationCount(new RegistrationCount(3)),
            new Money(1000),
            new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(300), new ImageHeight(200)),
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionStatus.RECRUITING));
    }

    static Stream<PaidSession> createSessionStatusPreparingSession() {
        return Stream.of(new PaidSession(
            new SessionName("강의이름"),
            new RegistrationCount(1),
            new MaxRegistrationCount(new RegistrationCount(3)),
            new Money(1000),
            new Image(new ImageSize(1), ImageType.JPG, new ImageWidth(300), new ImageHeight(200)),
            LocalDateTime.now(),
            LocalDateTime.now(),
            SessionStatus.PREPARING));
    }
}
