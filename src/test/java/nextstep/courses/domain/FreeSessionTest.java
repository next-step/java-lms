package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeSessionTest {

    public static FreeSession F1 = new FreeSession(1L, List.of(SessionImageTest.S1), RecruitStatus.RECRUIT, SessionDateTest.of());
    private final NsUser student = NsUserTest.JAVAJIGI;
    private final Long sessionId = 1L;

    @Test
    @DisplayName("무료 강의 수강신청 되는 지 테스트")
    void testEnrollment() {
        FreeSession freeSession = new FreeSession(sessionId, List.of(SessionImageTest.S1), RecruitStatus.RECRUIT, SessionDateTest.of());
        freeSession.enrollmentUser(student, new Payment());

        assertThat(freeSession.getStudents()).hasSize(1).containsExactly(student);
    }

}
