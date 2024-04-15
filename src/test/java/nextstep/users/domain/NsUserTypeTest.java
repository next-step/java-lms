package nextstep.users.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.EnumSource.Mode;

class NsUserTypeTest {

    @Test
    void 강사이면_true를_반환한다() {
        assertThat(NsUserType.COACH.isCoach()).isTrue();
    }

    @Test
    void 선발된_인원이_아니면_true를_반환한다() {
        assertThat(NsUserType.USER.isNotSelected()).isTrue();
    }

    @ParameterizedTest
    @EnumSource(mode = Mode.INCLUDE, names = {"WOOTECO", "WOOTECAM"})
    void 선발된_인원이면_false를_반환한다(final NsUserType nsUserType) {
        assertThat(nsUserType.isNotSelected()).isFalse();
    }
}
