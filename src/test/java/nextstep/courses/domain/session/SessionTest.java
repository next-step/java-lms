package nextstep.courses.domain.session;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    public static Session S1;
    public static Session S2;

    @BeforeEach
    void setUp() {
        S1 = new Session(1L, CoverImageTest.C1);
        S2 = new Session(2L, CoverImageTest.C1, new SessionEnrollment(SessionStatus.RECRUITING, 0));
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    void canEnrollSessionIsRecruiting() {
        assertThatIllegalStateException().isThrownBy(() -> S1.register(new SessionUser(1L, 1L)));
    }

    @Test
    @DisplayName("강의는 최대 수강인원을 초과할 수 없다.")
    void maxStudentSize() {
        assertThatIllegalStateException().isThrownBy(() -> S1.register(new SessionUser(1L, 1L)));
    }

}
