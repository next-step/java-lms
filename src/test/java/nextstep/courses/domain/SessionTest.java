package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private static final LocalDate START_DATE = LocalDate.of(2025, 11, 3);
    private static final LocalDate END_DATE = LocalDate.of(2025, 12, 18);

    @Test
    public void 정상적인_강의_생성() {
        SessionImage image = new SessionImage(500_000L, "png", 300, 200);

        Session session = new Session(START_DATE, END_DATE, image);

        assertThat(session).isNotNull();
    }

    @Test
    public void 종료일이_시작일보다_이전이면_예외() {
        LocalDate startDate = LocalDate.of(2026, 11, 3);
        LocalDate endDate = LocalDate.of(2025, 12, 18);
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);

        assertThatThrownBy(() -> new Session(startDate, endDate, image))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("종료일은 시작일보다 이후여야 한다");
    }


    @Test
    public void 준비중_상태일때_수강신청_불가() {
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);
        Session session = new Session(START_DATE, END_DATE, image, "준비중");

        assertThatThrownBy(() -> {
            session.enroll(1L);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있다");
    }

    @Test
    public void 모집중_상태일때_수강신청_가능() {
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);
        Session session = new Session(START_DATE, END_DATE, image, "모집중");

        session.enroll(1L);

        assertThat(session.isEnrolled(1L)).isTrue();
    }

    @Test
    public void 종료_상태일때_수강신청_불가() {
        SessionImage image = new SessionImage(500_000L, "png", 900, 600);
        Session session = new Session(START_DATE, END_DATE, image, "종료");

        assertThatThrownBy(() -> {
            session.enroll(1L);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있다");
    }
}
