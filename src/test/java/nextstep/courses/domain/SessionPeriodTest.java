package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class SessionPeriodTest {

    @Test
    @DisplayName("SessionPeriod_validate_test")
    public void SessionPeriod_validate_test(){
        assertThatIllegalArgumentException()
                .isThrownBy(() -> {
                    new SessionPeriod(LocalDate.of(2021,1,1), LocalDate.of(2020,1,1));
                });
    }

    @Test
    @DisplayName("isNotEnrollment_Test")
    public void isNotEnrollment_Test(){
        assertThat(SessionEnrollment.isNotEnrollment(SessionEnrollment.ENROLLMENT)).isFalse();
        assertThat(SessionEnrollment.isNotEnrollment(SessionEnrollment.NON_ENROLLMENT)).isTrue();
    }

}