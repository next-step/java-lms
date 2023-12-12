package nextstep.courses.type;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class EnrollmentStateTest {


    @Test
    public void validateState() {
        RecruitState recruiting = RecruitState.RECRUITING;
        RecruitState end = RecruitState.CLOSED;

        Assertions.assertThat(recruiting.recruiting()).isTrue();
        Assertions.assertThat(end.recruiting()).isFalse();
    }
}
