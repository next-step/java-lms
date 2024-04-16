package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AttendeeCountTest {

    public static AttendeeCount attendeeCount = new AttendeeCount(20);

    @Test
    void can_sign_up() {
        assertThat(attendeeCount.canSignUp()).isTrue();
    }

    @Test
    void limit_exist() {
        assertThat(attendeeCount.limitExist()).isTrue();
    }
}