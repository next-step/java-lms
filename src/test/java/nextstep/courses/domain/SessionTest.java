package nextstep.courses.domain;

import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.courses.domain.utils.TestSessionFactory;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {

    private final Long sessionId = 1L;
    private final NsUser student = NsUserTest.JAVAJIGI;

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RECRUIT"})
    @DisplayName("모집 중이 아닌 강의에 수강신청 하는 경우 에러 발생")
    void testInvalidEnrollmentUser(SessionStatus sessionStatus) {
        Session testSession = TestSessionFactory.makeSession(sessionId, sessionStatus);

        assertThatThrownBy(() -> testSession.enrollmentUser(student, new Payment())).isInstanceOf(NotRecruitException.class);
    }


    @Test
    @DisplayName("수강신청한 사람이 한 번 더 수강신청할 경우 에러 발생")
    void testDuplicateEnrollment() {
        Session testSession = TestSessionFactory.recruitStatusSession(sessionId);
        testSession.enrollmentUser(student, new Payment());

        assertThatThrownBy(() -> testSession.enrollmentUser(student, new Payment())).isInstanceOf(NotRecruitException.class);
    }
}
