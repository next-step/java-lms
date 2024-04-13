package nextstep.courses.domain;

import nextstep.courses.domain.exception.LackPointException;
import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaySessionTest {

    private final int maximumStudents = 5;
    private final int amount = 1000;

    private NsUser student = NsUserTest.JAVAJIGI;

    @BeforeEach
    public void chargePoint() {
        student.chargePoint(amount);
    }

    @AfterEach
    public void minusPoint() {
        student.minusPoint(amount);
    }

    @Test
    @DisplayName("유료 강의 수강신청 되는 지 테스트")
    void testEnrollment() {
        PaySession paySession = new PaySession(SessionImageTest.S1, SessionStatus.RECRUIT, maximumStudents, amount);
        Payment payment = paySession.enrollmentUser(student);

        assertThat(payment.getSessionId()).isEqualTo(paySession.getId());
        assertThat(payment.getAmount()).isEqualTo(amount);
        assertThat(payment.getNsUserId()).isEqualTo(student.getId());
        assertThat(paySession.getStudents()).hasSize(1).containsExactly(student);
    }

    @Test
    @DisplayName("수강신청한 사람이 한 번 더 수강신청할 경우 에러 발생")
    void testDuplicateEnrollment() {
        PaySession paySession = new PaySession(SessionImageTest.S1, SessionStatus.RECRUIT, maximumStudents, amount);
        paySession.enrollmentUser(student);

        assertThatThrownBy(() -> paySession.enrollmentUser(student)).isInstanceOf(NotRecruitException.class);
    }

    @Test
    @DisplayName("수강신청 인원을 넘을 경우 에러 발생")
    void testOverMaximumStudents() {
        PaySession paySession = new PaySession(SessionImageTest.S1, SessionStatus.RECRUIT, 1, amount);
        paySession.enrollmentUser(student);

        assertThatThrownBy(() -> paySession.enrollmentUser(NsUserTest.SANJIGI)).isInstanceOf(NotRecruitException.class);
    }

    @Test
    @DisplayName("결제 금액이 모자란 경우 에러 발생")
    void testLackPoint() {
        PaySession paySession = new PaySession(SessionImageTest.S1, SessionStatus.RECRUIT, 1, amount+1);

        assertThatThrownBy(() -> paySession.enrollmentUser(student)).isInstanceOf(LackPointException.class);
    }

    @ParameterizedTest
    @EnumSource(mode = EnumSource.Mode.EXCLUDE, names = {"RECRUIT"})
    @DisplayName("모집 중이 아닌 강의에 수강신청 하는 경우 에러 발생")
    void testInvalidEnrollmentUser(SessionStatus sessionStatus) {
        PaySession paySession = new PaySession(SessionImageTest.S1, sessionStatus, 1, amount+1);

        assertThatThrownBy(() -> paySession.enrollmentUser(student)).isInstanceOf(NotRecruitException.class);
    }

}
