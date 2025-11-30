package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    private static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);
    private static final SessionImage IMAGE =new SessionImage(500_000L, "png", 300, 200);

    @Test
    public void 정상적인_강의_생성() {

        Session session = new Session(START_DATE, END_DATE, IMAGE);

        assertThat(session).isNotNull();
    }

    @Test
    public void 준비중_상태일때_수강신청_불가() {
        Session session = new Session(START_DATE, END_DATE, IMAGE, "준비중");

        assertThatThrownBy(() -> {
            session.enroll(1L);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있다");
    }

    @Test
    public void 모집중_상태일때_수강신청_가능() {
        Session session = new Session(START_DATE, END_DATE, IMAGE, "모집중");

        session.enroll(1L);

        assertThat(session.isEnrolled(1L)).isTrue();
    }

    @Test
    public void 종료_상태일때_수강신청_불가() {
        Session session = new Session(START_DATE, END_DATE, IMAGE, "종료");

        assertThatThrownBy(() -> {
            session.enroll(1L);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있다");
    }

    @Test
    public void 유료강의의_최대인원을_초과하면_예외() {
        long fee = 100_000L;
        Session session = new Session(START_DATE,END_DATE, IMAGE, "모집중",  2, fee);

        session.enroll(1L, fee);
        session.enroll(2L, fee);

        assertThatThrownBy(() -> {
            session.enroll(3L, fee);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("최대 수강 인원을 초과");
    }

    @Test
    public void 유료강의의_결제금액이_수강료와_일치하지_않으면_예외() {
        long fee = 100_000L;
        Session session = new Session(START_DATE, END_DATE, IMAGE, "모집중", 10, fee);

        assertThatThrownBy(() -> {
            session.enroll(1L, 50_000L);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다");
    }

    @Test
    public void 유료강의_결제금액이_수강료와_일치하면_수강성공() {
        long fee = 100_000L;
        Session session = new Session(START_DATE,END_DATE, IMAGE, "모집중", 10, fee);

        session.enroll(1L, fee);

        assertThat(session.isEnrolled(1L)).isTrue();
    }


}
