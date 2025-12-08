package nextstep.courses.domain.session;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class EnrollmentTest {

    @Test
    void 수강신청_정상_생성() {
        Enrollment enrollment = new Enrollment(NsUserTest.JAVAJIGI, 1L, LocalDateTime.now(), null);
        assertThat(enrollment).isNotNull();
    }

}