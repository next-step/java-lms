package nextstep.sessions.domain.data.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionRecruitingStateTest {

    @Test
    void isRecruiting() {
        assertThat(SessionRecruitingState.NO_RECRUITING.isRecruiting()).isFalse();
        assertThat(SessionRecruitingState.RECRUITING.isRecruiting()).isTrue();
    }
}
