package nextstep.courses.domain;

import nextstep.courses.domain.exception.LackPointException;
import nextstep.courses.domain.exception.NotRecruitException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaySessionTest {
    public static PaySession P1 = new PaySession(1L, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of(), 5, 1000);
    private final int maximumStudents = 5;
    private final long amount = 1000;
    private final Long sessionId = 1L;

    private NsUser student = NsUserTest.JAVAJIGI;

    private Payment payment = new Payment("1", sessionId, student.getId(), amount);

    @Test
    @DisplayName("유료 강의 수강신청 되는 지 테스트")
    void testEnrollment() {
        PaySession paySession = new PaySession(sessionId, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of(), maximumStudents, amount);
        paySession.enrollmentUser(student, payment);

        assertThat(paySession.getStudents()).hasSize(1).containsExactly(student);
    }



    @Test
    @DisplayName("수강신청 인원을 넘을 경우 에러 발생")
    void testOverMaximumStudents() {
        PaySession paySession = new PaySession(sessionId, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of(), 1, amount);
        paySession.enrollmentUser(student, payment);

        assertThatThrownBy(() -> paySession.enrollmentUser(NsUserTest.SANJIGI, payment)).isInstanceOf(NotRecruitException.class);
    }

    @Test
    @DisplayName("결제 금액이 모자란 경우 에러 발생")
    void testLackPoint() {
        PaySession paySession = new PaySession(sessionId, SessionImageTest.S1, RecruitStatus.RECRUIT, SessionDateTest.of(), 1, amount + 1);
        Payment payment = new Payment("1", sessionId, student.getId(), amount -1);

        assertThatThrownBy(() -> paySession.enrollmentUser(student, payment)).isInstanceOf(LackPointException.class);
    }

}
