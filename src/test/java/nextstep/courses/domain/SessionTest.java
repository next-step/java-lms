package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

public class SessionTest {

    @Test
    @DisplayName("Validate_date_test")
    public void Validate_date_test() {
        LocalDate startedAt = LocalDate.of(2022, 11, 10);
        LocalDate endedAt = LocalDate.of(2021, 11, 10);

        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    new Session(startedAt, endedAt, 1L);
                });
    }

    @Test
    @DisplayName("register_method_test")
    public void register_method_test() {
        Session session = new Session(SessionEnrollment.ENROLLMENT);
        session.register();

        assertThat(session.getNumberOfRegisteredStudent()).isEqualTo(1);
    }

    @Test
    @DisplayName("validate_capacityStudent_test")
    public void validate_capacityStudent_test() {
        Session session = new Session(2);
        session.register();
        session.register();

        assertThatIllegalStateException()
                .isThrownBy(session::register);
    }

    @Test
    @DisplayName("validate_Status_test")
    public void validate_Status_test() {
        Session session = new Session(SessionEnrollment.NON_ENROLLMENT);
        assertThatIllegalStateException()
                .isThrownBy(session::register);
    }
}
