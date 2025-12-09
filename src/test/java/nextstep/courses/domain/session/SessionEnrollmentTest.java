package nextstep.courses.domain.session;

import nextstep.courses.SessionUnregistrableException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class SessionEnrollmentTest {
    @Test
    void 최대_수강_인원을_초과하면_등록이_실패한다() {
        SessionEnrollment enrollment = new SessionEnrollment(1);
        enrollment.enroll(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> enrollment.enroll(NsUserTest.SANJIGI))
                .isInstanceOf(SessionUnregistrableException.class);
    }
}