package nextstep.sessions.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class RecruitmentStatusTest {
    @Test
    void isNotRecruiting() {
        Assertions.assertThat(RecruitmentStatus.RECRUITING.isNotRecruiting()).isFalse();
        Assertions.assertThat(RecruitmentStatus.NOT_RECRUITING.isNotRecruiting()).isTrue();
    }
}
