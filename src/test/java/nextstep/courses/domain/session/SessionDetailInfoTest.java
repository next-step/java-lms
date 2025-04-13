package nextstep.courses.domain.session;

import nextstep.courses.domain.session.info.detail.SessionDetailInfo;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionDetailInfoTest {
    private static final LocalDateTime START_DATE = LocalDateTime.now();
    private static final LocalDateTime END_DATE = START_DATE.plusMonths(1);

    @Test
    @DisplayName("강의의 상세 정보를 생성한다")
    void create() {
        SessionDetailInfo detailInfo = new SessionDetailInfo(
            START_DATE,
            END_DATE,
            SessionType.PAID,
            10000
        );

        assertThat(detailInfo.getType()).isEqualTo(SessionType.PAID);
        assertThat(detailInfo.getPriceValue()).isEqualTo(10000);
        assertThat(detailInfo.isPaid()).isTrue();
    }

    @Test
    @DisplayName("유료 강의의 결제를 검증한다")
    void validatePayment() {
        SessionDetailInfo detailInfo = new SessionDetailInfo(
            START_DATE,
            END_DATE,
            SessionType.PAID,
            10000
        );
        Payment payment = new Payment("payment1", 1L, 1L, 10000L);

        assertThatThrownBy(() -> detailInfo.validatePayment(new Payment("payment1", 1L, 1L, 5000L)))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("결제 금액이 수강료와 일치하지 않습니다.");

        detailInfo.validatePayment(payment);
    }

    @Test
    @DisplayName("무료 강의는 결제 검증이 필요 없다")
    void validateFreeSession() {
        SessionDetailInfo detailInfo = new SessionDetailInfo(
            START_DATE,
            END_DATE,
            SessionType.FREE,
            0
        );

        assertThat(detailInfo.getType()).isEqualTo(SessionType.FREE);
        assertThat(detailInfo.getPriceValue()).isEqualTo(0);
        assertThat(detailInfo.isPaid()).isFalse();
    }
} 