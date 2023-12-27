package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.courses.fixture.SessionFixtures;
import nextstep.payments.fixture.PaymentFixtures;
import nextstep.users.fixtures.NsUserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentTest {
    private Session session;
    private Enrollment enrollment;

    @Test
    @DisplayName("수강 신청은 모집 중, 준비 중이면 새로운 신청을 반환 한다.")
    void apply_recruit_ready_success() {
        session = SessionFixtures.createdChargedSession(
                SessionRecruitStatus.RECRUIT, SessionProgressStatus.READY
        );

        enrollment = session.enrollment();
        Apply newApply = enrollment.apply(
                NsUserFixtures.TEACHER_APPLE_3L.getId(),
                PaymentFixtures.payment(1L, 3L),
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(newApply.sessionId()).isEqualTo(session.id());
        assertThat(newApply.nsUserId()).isEqualTo(NsUserFixtures.TEACHER_APPLE_3L.getId());
    }

    @Test
    @DisplayName("수강 신청은 모집 중, 진행 중이면 새로운 신청을 반환 한다.")
    void apply_recruit_ongoing_success() {
        session = SessionFixtures.createdChargedSession(
                SessionRecruitStatus.RECRUIT, SessionProgressStatus.ONGOING
        );

        enrollment = session.enrollment();
        Apply newApply = enrollment.apply(
                NsUserFixtures.TEACHER_APPLE_3L.getId(),
                PaymentFixtures.payment(1L, 3L),
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(newApply.sessionId()).isEqualTo(session.id());
        assertThat(newApply.nsUserId()).isEqualTo(NsUserFixtures.TEACHER_APPLE_3L.getId());
    }

    @Test
    @DisplayName("수강 신청은 비 모집 중이면 신청할 수 없다는 예외를 반환 한다.")
    void apply_notRecruitStatus_throwsException() {
        session = SessionFixtures.createdChargedSession(
                SessionRecruitStatus.NOT_RECRUIT, SessionProgressStatus.ONGOING
        );
        enrollment = session.enrollment();

        assertThatThrownBy(
                () -> enrollment.apply(
                        NsUserFixtures.TEACHER_APPLE_3L.getId(),
                        PaymentFixtures.payment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 모집 중, 종료 라면 신청할 수 없다는 예외를 반환 한다.")
    void apply_recruitStatus_endStatus_throwsException() {
        session = SessionFixtures.createdChargedSession(
                SessionRecruitStatus.RECRUIT, SessionProgressStatus.END
        );
        enrollment = session.enrollment();

        assertThatThrownBy(
                () -> enrollment.apply(
                        NsUserFixtures.TEACHER_APPLE_3L.getId(),
                        PaymentFixtures.payment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의의 경우, 수강 인원 정원을 초과 하면 신청할 수 없다는 예외를 반환 한다.")
    void apply_chargeSession_overQuota_throwsException() {
        session = SessionFixtures.chargedSessionFullCanceled();
        enrollment = session.enrollment();

        assertThatThrownBy(
                () -> enrollment.apply(
                        NsUserFixtures.TEACHER_APPLE_3L.getId(),
                        PaymentFixtures.payment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 결제가 안 되었다면 신청할 수 없다는 예외를 반환 한다.")
    void apply_chargeSession_notPaid_throwsException() {
        session = SessionFixtures.createdChargedSession();
        enrollment = session.enrollment();

        assertThatThrownBy(
                () -> enrollment.apply(
                        NsUserFixtures.TEACHER_APPLE_3L.getId(),
                        null,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 수강 금액과 지불 금액이 다르면 신청할 수 없다는 예외를 던진다.")
    void apply_chargeSession_differentAmount_throwsException() {
        session = SessionFixtures.createdChargedSession();
        enrollment = session.enrollment();

        assertThatThrownBy(
                () -> enrollment.apply(
                        NsUserFixtures.TEACHER_APPLE_3L.getId(),
                        PaymentFixtures.differentPayment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
