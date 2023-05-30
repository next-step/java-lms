package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionStatus.RECRUITING;
import static org.assertj.core.api.Assertions.assertThat;

class RegisterTest {
    @Test
    void create() {
        Register register = new RegisterBuilder()
                .withStatus(RECRUITING)
                .withMaxRegisterCount(3)
                .withStudents(null)
                .build();

        assertThat(register).isNotNull();
    }
}
