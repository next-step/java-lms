package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    public static final Session S1 = new Session(1L, "TDD with JAVA 16", 16, LocalDate.now(), LocalDate.now(),
            SessionType.FREE, SessionStatus.PREPARING, 50, 12);
    public static final Session S2 = new Session(2L, "TDD with Kotlin 5", 5, LocalDate.now(), LocalDate.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50, 50);
    public static final Session S3 = new Session(3L, "DDD", 1, LocalDate.now(), LocalDate.now(),
            SessionType.PAID, SessionStatus.RECRUITING, 50, 2);

    @Test
    void create() {
        assertThat(S1).isNotNull();
    }

    @Test
    void 강의_모집기간이_아닙니다_예외처리() {
        assertThatThrownBy(() -> S1.isRegistrable())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("강의 모집기간이 아닙니다.");
    }

    @Test
    void 등록_인원_정원_초과_예외처리() {
        assertThatThrownBy(() -> S2.isRegistrable())
                .isInstanceOf(RuntimeException.class)
                .hasMessage("등록 인원이 정원 초과 되었습니다.");
    }

    @Test
    void 수강_신청_가능시_현재_등록_인원_1증가() {
        assertThat(S3.currRegisterNum()).isEqualTo(2);
        S3.isRegistrable();
        assertThat(S3.currRegisterNum()).isEqualTo(3);
    }

}
