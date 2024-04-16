package nextstep.courses.domain.session.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import nextstep.courses.domain.cover.Image;
import nextstep.courses.domain.cover.ImageHeight;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.cover.ImageType;
import nextstep.courses.domain.cover.ImageWidth;
import nextstep.courses.domain.session.SessionName;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

class FreeSessionTest {

    @Test
    void 무료_강의_수강신청시_강의_상태가_모집중이라면_수강_신청이_가능하다() {
        FreeSession freeSession = new FreeSession(
            new SessionName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            SessionStatus.RECRUITING);

        assertThat(freeSession.isRegistrationAvailable()).isTrue();
    }

    @Test
    void 무료_강의_수강신청시_강의_상태가_모집중이_아니라면_수강_신청이_불가능하다() {
        FreeSession freeSession = new FreeSession(
            new SessionName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            SessionStatus.END);

        assertThat(freeSession.isRegistrationAvailable()).isFalse();
    }

    @Test
    void 무료_강의_수강신청시_강의_수강료가_0원일_경우_수강_신청이_가능하다() {
        FreeSession freeSession = new FreeSession(
            new SessionName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            SessionStatus.RECRUITING);

        assertThat(freeSession.isPaymentAmountSameTuitionFee(
            new Payment("1L", 1L, 1L, new Money(0)))).isTrue();
    }

    @Test
    void 무료_강의_수강신청시_강의_수강료가_0원이_아닐_경우_수강_신청이_불가능하다() {
        FreeSession freeSession = new FreeSession(
            new SessionName("강의이름"),
            LocalDateTime.now(),
            LocalDateTime.now(),
            new Image(new ImageSize(1),
                ImageType.JPG,
                new ImageWidth(300),
                new ImageHeight(200)),
            SessionStatus.RECRUITING);

        assertThat(freeSession.isPaymentAmountSameTuitionFee(
            new Payment("1L", 1L, 1L, new Money(9999)))).isFalse();
    }
}
