package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class SessionStatusTest {

    @Test
    void 생성자테스트() {
        Assertions.assertThat(new SessionStatus(30)).isInstanceOf(SessionStatus.class);
    }

    @Test
    void 중복등록불가테스트() {
        SessionStatus sessionStatus = new SessionStatus(30);

        sessionStatus.signUp("jerry");

        assertThatThrownBy(() -> {
            sessionStatus.signUp("jerry");
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("이미 등록된 학생입니다.");
    }

    @Test
    void 최대인원초과테스트() {
        SessionStatus sessionStatus = new SessionStatus(1);

        sessionStatus.signUp("jerry");

        assertThatThrownBy(() -> {
            sessionStatus.signUp("david");
        }).isInstanceOf(RuntimeException.class).hasMessageContaining("최대 수강 인원을 초과하여 신청이 불가합니다.");
    }
}
