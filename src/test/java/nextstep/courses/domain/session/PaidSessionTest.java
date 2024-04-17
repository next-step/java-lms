package nextstep.courses.domain.session;

import nextstep.courses.constant.SessionStatus;
import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.SessionImageTest;
import nextstep.payments.domain.PaymentTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PaidSessionTest {
    @Test
    @DisplayName("PaidSession 생성 테스트")
    public void initPaidSession() {
        PaidSession paidSession = new PaidSession(
                CourseTest.COURSE,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.READY,
                2,
                10000L
        );

        assertThat(paidSession).isInstanceOf(PaidSession.class);
    }

    @Test
    @DisplayName("PaidSession 등록 테스트")
    public void enrollPaidSession() {
        PaidSession paidSession = new PaidSession(
                1L,
                1L,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.RECRUITING,
                2,
                10000L
        );

        paidSession.enroll(NsUserTest.NEWUSER, PaymentTest.PAYMENT);

        assertThat(paidSession.getEnrolledStudentCount()).isEqualTo(1);
        assertThat(paidSession.isEnrolled(NsUserTest.NEWUSER)).isTrue();
    }

    @Test
    @DisplayName("PaidSession 등록 테스트, 모집중이 아닌 세션 등록 예외 처리 테스트")
    public void enrollNotRecruitingSession() {
        PaidSession paidSession = new PaidSession(
                1L,
                1L,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.END,
                2,
                10000L
        );

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.NEWUSER, PaymentTest.PAYMENT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 모집중인 세션이 아닙니다.");
    }

    @Test
    @DisplayName("PaidSession 등록 테스트, Payment 금액 미일치 예외 처리 테스트")
    public void enrollWithNotMatchedPaymentSession() {
        PaidSession paidSession = new PaidSession(
                1L,
                1L,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.RECRUITING,
                2,
                10000L
        );

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.NEWUSER, PaymentTest.LESS_PAYMENT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("결제 금액이 일치하지 않습니다.");
    }

    @Test
    @DisplayName("PaidSession 등록 테스트, 수강 인원 초과 예외 처리 테스트")
    public void enrollWithOverEnrollmentSession() {
        PaidSession paidSession = new PaidSession(
                1L,
                1L,
                SessionImageTest.SESSION_IMAGE,
                LocalDateTime.now().minusDays(5),
                LocalDateTime.now().plusDays(5),
                SessionStatus.RECRUITING,
                2,
                10000L
        );

        paidSession.enrollStudent(NsUserTest.JAVAJIGI);
        paidSession.enrollStudent(NsUserTest.SANJIGI);

        assertThatThrownBy(() -> paidSession.enroll(NsUserTest.NEWUSER, PaymentTest.PAYMENT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("수강 인원이 초과되었습니다.");
    }
}
