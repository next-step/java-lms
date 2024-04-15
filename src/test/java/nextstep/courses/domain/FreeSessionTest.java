package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeSessionTest {

    public static FreeSession F1 = new FreeSession(1L, SessionImageTest.S1, SessionStatus.RECRUIT, SessionDateTest.of());
    private final NsUser student = NsUserTest.JAVAJIGI;
    private final Long sessionId = 1L;

    @Test
    @DisplayName("무료 강의 수강신청 되는 지 테스트")
    void testEnrollment() {
        FreeSession freeSession = new FreeSession(sessionId, SessionImageTest.S1, SessionStatus.RECRUIT, SessionDateTest.of());
        freeSession.enrollmentUser(student, new Payment());

        assertThat(freeSession.getStudents()).hasSize(1).containsExactly(student);
    }

}
