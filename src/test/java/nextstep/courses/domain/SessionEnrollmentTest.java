package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionEnrollmentTest {

    public static SessionEnrollment SE1 = new SessionEnrollment(SessionStatus.PREPARING, 1);
    public static SessionEnrollment SE2 = new SessionEnrollment(SessionStatus.RECRUITING, 0);

    @Test
    void 모집중에만_수강신청가능() {
        assertThatIllegalStateException().isThrownBy(() -> SE1.enroll(new NsUser()));
    }

    @Test
    void 모집인원을_초과할수없다() {
        assertThatIllegalStateException().isThrownBy(() -> SE2.enroll(new NsUser()));
    }
}
