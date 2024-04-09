package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SessionStatusTest {

    @Test
    void 현재_상태가_모집중이면_false를_반환한다() {
        // given
        final SessionStatus sessionStatus = SessionStatus.RECRUITING;

        // when
        final boolean actual = sessionStatus.isNotRecruiting();

        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = SessionStatus.class, mode = EnumSource.Mode.EXCLUDE, names = "RECRUITING")
    void 현재_상태가_모집중이_아니면_true를_반환한다(final SessionStatus sessionStatus) {
        assertThat(sessionStatus.isNotRecruiting()).isTrue();
    }
}
