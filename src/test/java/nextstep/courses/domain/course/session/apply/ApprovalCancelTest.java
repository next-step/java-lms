package nextstep.courses.domain.course.session.apply;

import nextstep.courses.domain.course.session.Session;
import nextstep.courses.fixture.ApplyFixtures;
import nextstep.courses.fixture.SessionFixtures;
import nextstep.users.fixtures.NsUserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ApprovalCancelTest {
    private Session session;
    private ApproveCancel approveCancel;

    @Test
    @DisplayName("approve 는 선생님인 경우 수강생의 강의 신청을 승인한다.")
    void approve_teacher_changeApproveTrue() {
        session = SessionFixtures.chargedSessionFullCanceled();
        approveCancel = session.approve();

        Apply changedApply = approveCancel.approve
                (NsUserFixtures.TEACHER_JAVAJIGI_1L,
                        ApplyFixtures.apply_one_canceled(),
                        SessionFixtures.DATETIME_2023_12_5
                );

        assertThat(changedApply.approval()).isEqualTo(ApprovalStatus.APPROVED);
    }

    @Test
    @DisplayName("approve 는 학생인 경우 권한이 없다는 예외를 던진다.")
    void approve_student_throwsException() {
        session = SessionFixtures.chargedSessionFullCanceled();
        approveCancel = session.approve();

        assertThatThrownBy(
                () -> approveCancel.approve(
                        NsUserFixtures.STUDENT_ERIC_4L,
                        ApplyFixtures.apply_one_canceled(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("approve 는 이미 수강 승인이 되었으면 예외를 던진다.")
    void approve_alreadyApproved_throwsException() {
        session = SessionFixtures.chargedSessionFullApproved();
        approveCancel = session.approve();

        assertThatThrownBy(
                () -> approveCancel.approve(
                        NsUserFixtures.TEACHER_JAVAJIGI_1L,
                        ApplyFixtures.apply_one_approved(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("cancel 는 선생님인 경우 수강생의 강의 신청을 취소한다.")
    void cancel_teacher_changeApproveTrue() {
        session = SessionFixtures.chargedSessionFullApproved();
        approveCancel = session.approve();

        Apply changedApply = approveCancel.cancel(
                NsUserFixtures.TEACHER_JAVAJIGI_1L,
                ApplyFixtures.apply_one_canceled(),
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(changedApply.approval()).isEqualTo(ApprovalStatus.CANCELED);
    }

    @Test
    @DisplayName("cancel 는 학생인 경우 권한이 없다는 예외를 던진다.")
    void cancel_student_throwsException() {
        session = SessionFixtures.chargedSessionFullApproved();
        approveCancel = session.approve();

        assertThatThrownBy(
                () -> approveCancel.cancel(
                        NsUserFixtures.STUDENT_ERIC_4L,
                        ApplyFixtures.apply_one_approved(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("cancel 는 이미 수강 취소가 되었으면 예외를 던진다.")
    void cancel_alreadyCanceled_throwsException() {
        session = SessionFixtures.chargedSessionFullCanceled();
        approveCancel = session.approve();

        assertThatThrownBy(
                () -> approveCancel.cancel(
                        NsUserFixtures.TEACHER_JAVAJIGI_1L,
                        ApplyFixtures.apply_one_canceled(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
