package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;

class SessionUserTest {
    @Test
    void 최대_수강_인원을_초과할_수_없다() {
        int maxEnrollment = 1;
        SessionUser sessionUser = new SessionUser(maxEnrollment);
        sessionUser.addEnroll(JAVAJIGI);
        Assertions.assertThatThrownBy(() -> sessionUser.addEnroll(JAVAJIGI)).isInstanceOf(IllegalArgumentException.class);
    }
}