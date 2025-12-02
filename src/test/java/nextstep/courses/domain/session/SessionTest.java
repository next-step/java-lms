package nextstep.courses.domain.session;

import nextstep.courses.domain.session.image.SessionImage;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    private static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);
    private static final SessionImage IMAGE = new SessionImage(500_000L, "png", 300, 200);

    @Test
    public void 정상적인_강의_생성() {
        Session session = new Session(START_DATE, END_DATE, IMAGE, "준비중");

        assertThat(session).isNotNull();
    }

    @Test
    public void 기수_정보를_가진_강의_생성() {
        int cohort = 1;

        Session session = new Session(cohort, START_DATE, END_DATE, IMAGE);

        assertThat(session.getCohort()).isEqualTo(cohort);
    }


    @Test
    public void 모집중_상태일때_수강신청_가능() {
        Session session = new Session(START_DATE, END_DATE, IMAGE, "모집중");

        session.enroll(1L);

        assertThat(session.isEnrolled(1L)).isTrue();
    }

    @Test
    public void 유료강의의_결제금액이_수강료와_일치하지_않으면_예외() {
        long fee = 100_000L;
        Session session = new Session(START_DATE, END_DATE, IMAGE, "모집중", 10, fee);

        assertThatThrownBy(() -> {
            session.enroll(1L, new Payment("결제번호-1", 1L, 1L, 50_000L));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다");
    }

    @Test
    public void 유료강의_결제금액이_수강료와_일치하면_수강성공() {
        long fee = 100_000L;
        Session session = new Session(START_DATE, END_DATE, IMAGE, "모집중", 10, fee);

        session.enroll(1L, new Payment("결제번호-1", 1L, 1L, fee));

        assertThat(session.isEnrolled(1L)).isTrue();
    }


}
