package nextstep.courses.domain;

import nextstep.courses.exception.SessionUserCountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionUserCountTest {

    public static SessionUserCount zeroSessionUserCount() {
        return new SessionUserCount( 10);
    }

    public static SessionUserCount maxSessionUserCount() {
        return new SessionUserCount(10, 10);
    }

    @Test
    @DisplayName("성공 - 강의 수강 인원이 증가한다.")
    void success_plus_session_user_count() {
        SessionUserCount sessionUserCount = zeroSessionUserCount();
        sessionUserCount.plusUserCount();
        assertThat(sessionUserCount.userCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("성공 - 강의 수강 인원이 제한된 수강 인원 이상일 경우 예외가 발생한다.")
    void testMethodNameHere() {
        SessionUserCount sessionUserCount = maxSessionUserCount();
        assertThatThrownBy(sessionUserCount::validateMaxUserCount)
                .isInstanceOf(SessionUserCountException.class)
                .hasMessage("제한된 수강 신청 인원을 초과 하였습니다.");
    }

}
