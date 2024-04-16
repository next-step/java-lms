package nextstep.courses.domain.session.impl;

import nextstep.courses.domain.cover.*;
import nextstep.courses.domain.session.SessionStatus;
import nextstep.courses.fixture.builder.FreeSessionBuilder;
import nextstep.courses.fixture.builder.ImageBuilder;
import nextstep.payments.domain.Money;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FreeSessionTest {

    @Test
    void 무료_강의_수강신청시_강의_상태가_모집중이라면_수강_신청이_가능하다() {
        FreeSession freeSession = FreeSessionBuilder.anFreeSession()
            .withName("강의이름")
            .withStatus(SessionStatus.RECRUITING)
            .withImage(ImageBuilder.anImage()
                .withSize(new ImageSize(1))
                .withType(ImageType.JPG)
                .withWidth(new ImageWidth(300))
                .withHeight(new ImageHeight(200))
                .build())
            .build();

        assertThat(freeSession.isRegistrationAvailable()).isTrue();
    }

    @Test
    void 무료_강의_수강신청시_강의_상태가_모집중이_아니라면_수강_신청이_불가능하다() {
        FreeSession freeSession = FreeSessionBuilder.anFreeSession()
            .withName("강의이름")
            .withStatus(SessionStatus.END)
            .withImage(ImageBuilder.anImage()
                .withSize(new ImageSize(1))
                .withType(ImageType.JPG)
                .withWidth(new ImageWidth(300))
                .withHeight(new ImageHeight(200))
                .build())
            .build();

        assertThat(freeSession.isRegistrationAvailable()).isFalse();
    }

    @Test
    void 무료_강의_수강신청시_강의_수강료가_0원일_경우_수강_신청이_가능하다() {
        FreeSession freeSession = FreeSessionBuilder.anFreeSession()
            .withName("강의이름")
            .withStatus(SessionStatus.RECRUITING)
            .withImage(ImageBuilder.anImage()
                .withSize(new ImageSize(1))
                .withType(ImageType.JPG)
                .withWidth(new ImageWidth(300))
                .withHeight(new ImageHeight(200))
                .build())
            .build();

        assertThat(freeSession.isPaymentAmountSameTuitionFee(
            new Payment("1L", 1L, 1L, new Money(0)))).isTrue();
    }

    @Test
    void 무료_강의_수강신청시_강의_수강료가_0원이_아닐_경우_수강_신청이_불가능하다() {
        FreeSession freeSession = FreeSessionBuilder.anFreeSession()
            .withName("강의이름")
            .withStatus(SessionStatus.RECRUITING)
            .withImage(ImageBuilder.anImage()
                .withSize(new ImageSize(1))
                .withType(ImageType.JPG)
                .withWidth(new ImageWidth(300))
                .withHeight(new ImageHeight(200))
                .build())
            .build();

        assertThat(freeSession.isPaymentAmountSameTuitionFee(
            new Payment("1L", 1L, 1L, new Money(9999)))).isFalse();
    }
}
