package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessionTest {

    @Test
    void 수강신청_가능() {
        Session session = new Session(SessionStatus.ENROLLING);
        assertThat(session.isEnroll()).isTrue();
    }

}
