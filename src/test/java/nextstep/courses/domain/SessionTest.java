package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    SessionPeriod period;
    NsImage image;
    Price price;
    Session freeSession;
    Session paidSession;
    Payment payment_1000;
    Payment payment_500;

    @BeforeEach
    void setUp() {
        LocalDate start = LocalDate.now().minusDays(1);
        LocalDate end = LocalDate.now().plusDays(5);
        period = new SessionPeriod(start, end);
        image = new NsImage(500_000, "image/png", 300, 200);
        price = Price.of(1000L);
        freeSession = Session.createFree(1L, period, image);
        paidSession = Session.createPaid(2L, period, image, 1, price);
        payment_1000 = new Payment("1", 2L, 1L, 1000L);
        payment_500 = new Payment("2", 2L, 1L, 500L);
    }

    @Test
    void 무료_강의_생성_테스트() {
        assertThat(freeSession.isFree()).isTrue();
        assertThat(freeSession.hasRoom()).isTrue();
    }

    @Test
    void 유료_강의_생성_테스트() {
        assertThat(paidSession.isFree()).isFalse();
        assertThat(paidSession.hasRoom()).isTrue();
    }

    @Test
    void 무료_모집중이면_수강신청_가능() {
        assertThat(freeSession.canApply()).isFalse();
        Session recruitingSession = freeSession.startRecruiting();
        assertThat(recruitingSession.canApply()).isTrue();
    }

    @Test
    void 무료_종료면_수강신청_불가능() {
        assertThat(freeSession.canApply()).isFalse();
        Session recruitingSession = freeSession.startRecruiting();
        assertThat(recruitingSession.canApply()).isTrue();
        Session finishedSession = recruitingSession.finishRecruiting();
        assertThat(finishedSession.canApply()).isFalse();
    }

    @Test
    void 유료_모집중_정원미달이면_수강신청_가능() {
        Session recruitingSession = paidSession.startRecruiting();
        assertThat(recruitingSession.canApply()).isTrue();
    }

    @Test
    void 유료_모집중_정원마감이면_수강신청_불가능() {
        SessionMeta meta = new SessionMeta(SessionType.PAID, period, price, image);
        Capacity capacity = new LimitedCapacity(1, 1); // currentParticipants 생략
        Session session = new Session(1L, meta, SessionStatus.RECRUITING, capacity);
        assertThat(session.canApply()).isFalse();
    }

    @Test
    void 유료_종료면_수강신청_불가능() {
        assertThat(paidSession.finishRecruiting().canApply()).isFalse();
    }

    @Test
    void 유료_결제금액_수강료_일치시_수강성공() {
        SessionMeta meta = new SessionMeta(SessionType.PAID, period, price, image);
        Capacity capacity = new LimitedCapacity(1, 1); // currentParticipants 생략
        Session session = new Session(2L, meta, SessionStatus.PREPARING, capacity);

        assertThat(paidSession.startRecruiting().apply(payment_1000)).isEqualTo(session.startRecruiting());
    }

    @Test
    void 유료_결제금액_수강료_불일치시_수강실패() {
        assertThatThrownBy(() -> paidSession.startRecruiting().apply(payment_500))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액과 수강료가 일치하지 않습니다.");
    }
}
