package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

class SessionRecruitingStatusTest {

    @Test
    void 현재_상태가_모집중이면_false를_반환한다() {
        // given
        final SessionRecruitingStatus sessionRecruitingStatus = SessionRecruitingStatus.RECRUITING;

        // when
        final boolean actual = sessionRecruitingStatus.isNotRecruiting();

        // then
        assertThat(actual).isFalse();
    }

    @ParameterizedTest
    @EnumSource(value = SessionRecruitingStatus.class, mode = EnumSource.Mode.EXCLUDE, names = "RECRUITING")
    void 현재_상태가_모집중이_아니면_true를_반환한다(final SessionRecruitingStatus sessionRecruitingStatus) {
        assertThat(sessionRecruitingStatus.isNotRecruiting()).isTrue();
    }
}
