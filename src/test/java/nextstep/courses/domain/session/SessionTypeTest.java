package nextstep.courses.domain.session;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTypeTest {

    @DisplayName("최대 참가 인원수가 0명 이하면 에러를 발생시킵니다.")
    @Test
    void underZero() {
        // given
        // when
        // then
        Assertions.assertThatThrownBy(() -> SessionType.notFreeSession(0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("최대 참가 인원수는 0이하일 수 없습니다.");
    }

    @DisplayName("무료 강의라면 인원을 추가할 수 있기 때문에 true를 반환합니다.")
    @Test
    void free() {
        // given
        SessionType sessionType = SessionType.freeSession();
        // when
        boolean result = sessionType.canRegisterNewUser(1);
        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("유료 강의이면서 인원을 더 추가할 수 있으면 true를 반환합니다.")
    @Test
    void canAdd() {
        // given
        SessionType sessionType = SessionType.notFreeSession(2);
        // when
        boolean result = sessionType.canRegisterNewUser(1);
        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("유료 강의이면서 인원을 더이상 추가할 수 없으면 false를 반환합니다.")
    @Test
    void cannotAdd() {
        // given
        SessionType sessionType = SessionType.notFreeSession(1);
        // when
        boolean result = sessionType.canRegisterNewUser(1);
        // then
        Assertions.assertThat(result).isFalse();
    }
}