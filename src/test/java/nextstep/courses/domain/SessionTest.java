package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class SessionTest {

    public static final Session SESSION1 = new Session(1L, "JPA와 함께", 3, LocalDateTime.now(),
            LocalDateTime.now(), "http://image1.com", SessionState.RECRUITING, SessionType.PAY);
    public static final Session SESSION2 = new Session(2L, "마이바티스와 함께..", 3, LocalDateTime.now(),
            LocalDateTime.now(), "http://image2.com", SessionState.CLOSE, SessionType.FREE);

    @Test
    void applyTest_실패_모집중이_아님(){
        assertThatThrownBy(() -> SESSION2.apply(NsUserTest.JAVAJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("해당 강의는 수강신청중이 아닙니다.");
    }
}