package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    public static Session S1;
    public static Session S2;

    @BeforeEach
    void setUp() {
        S1 = new Session(0L,
                2L,
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                LocalDateTime.of(2022, 2, 22, 22, 22, 22),
                null,
                CoverImageTest.C1,
                PaymentType.FREE,
                SessionStatus.PREPARING,
                10);

        S2 = new Session(1L,
                2L,
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                LocalDateTime.of(2022, 2, 22, 22, 22, 22),
                null,
                CoverImageTest.C1,
                PaymentType.FREE,
                SessionStatus.RECRUITING,
                1);
    }

    @Test
    void newSession() {
        assertThat(S1).isEqualTo(new Session(0L,
                2L,
                LocalDateTime.MIN,
                LocalDateTime.MAX,
                LocalDateTime.of(2022, 2, 22, 22, 22, 22),
                null,
                CoverImageTest.C1,
                PaymentType.FREE,
                SessionStatus.PREPARING,
                10));
    }

    @Test
    @DisplayName("시작일은 종료일을 넘을 수 없다.")
    void validDate() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Session(0L,
                        2L,
                        LocalDateTime.MAX,
                        LocalDateTime.MIN,
                        LocalDateTime.of(2022, 2, 22, 22, 22, 22),
                        null,
                        CoverImageTest.C1,
                        PaymentType.FREE,
                        SessionStatus.PREPARING,
                        10));
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void canEnrollSessionIsRecruiting() {
        assertThatIllegalStateException().isThrownBy(() -> S1.register(new NsUser()));
    }

    @Test
    @DisplayName("강의는 최대 수강인원을 초과할 수 없다.")
    void maxStudentSize() {
        S2.register(new NsUser());
        assertThatIllegalStateException().isThrownBy(() -> S2.register(new NsUser()));
    }
}
