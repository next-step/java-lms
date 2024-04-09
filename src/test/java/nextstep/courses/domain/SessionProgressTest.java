package nextstep.courses.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionProgressTest {

    @Test
    void 디폴트_생성() {
        assertThat(new SessionProgress()).isEqualTo(new SessionProgress("준비중"));
    }

    @ParameterizedTest
    @ValueSource(strings = {"준비중", "모집중", "종료"})
    void 생성(String state) {
        assertThat(new SessionProgress(state)).isEqualTo(new SessionProgress(state));
    }

    @Test
    void 알맞지_않은_강의_상태() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new SessionProgress("진행중"))
                .withMessageMatching("알맞지 않은 강의 상태입니다.");
    }

    @Test
    void 강의_상태_변경() {
        SessionProgress process = new SessionProgress("준비중");
        process.update("모집중");
        assertThat(process).isEqualTo(new SessionProgress("모집중"));
    }
}
