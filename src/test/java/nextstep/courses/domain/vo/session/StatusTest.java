package nextstep.courses.domain.vo.session;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatusTest {
    @Test
    void ableRegister() {
        boolean ablePrepareRegister = Status.PREPARE.ableRegister();
        boolean ableRecruitingRegister = Status.RECRUITING.ableRegister();
        boolean ableCompletedRegister = Status.COMPLETED.ableRegister();

        Assertions.assertThat(ablePrepareRegister).isFalse();
        Assertions.assertThat(ableRecruitingRegister).isTrue();
        Assertions.assertThat(ableCompletedRegister).isFalse();
    }
}