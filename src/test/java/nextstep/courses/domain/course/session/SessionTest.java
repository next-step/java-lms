package nextstep.courses.domain.course.session;

import nextstep.courses.domain.course.session.apply.Apply;
import nextstep.courses.fixture.ApplyFixtures;
import nextstep.courses.fixture.ImageFixtures;
import nextstep.courses.fixture.SessionFixtures;
import nextstep.payments.fixture.PaymentFixtures;
import nextstep.users.fixtures.NsUserFixtures;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionTest {
    private Session session;

    @Test
    @DisplayName("강의는 이미지가 없으면 이미지를 추가하라는 예외를 반환 한다.")
    void newObject_imageNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(
                        null,
                        SessionFixtures.duration(),
                        SessionFixtures.freeSessionStateZero(),
                        1L,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 기간이 없으면 기간을 추가하라는 예외를 반환 한다.")
    void newObject_durationNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(
                        ImageFixtures.images(),
                        null,
                        SessionFixtures.freeSessionStateZero(),
                        1L,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("강의는 강의 상태가 없으면 상태를 추가하라는 예외를 반환 한다.")
    void newObject_sessionStateNull_throwsException() {
        assertThatThrownBy(
                () -> new Session(
                        ImageFixtures.images(),
                        SessionFixtures.duration(),
                        null,
                        1L,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 모집 중, 준비 중이면 해당 인원이 추가 된다.")
    void apply_recruit_ready_success() {
        session = SessionFixtures.createdChargedSession(SessionRecruitStatus.RECRUIT, SessionProgressStatus.READY);

        int size = session.applyCount();

        session.apply(
                NsUserFixtures.TEACHER_APPLE_3L,
                PaymentFixtures.payment(1L, 3L),
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(session.applyCount()).isEqualTo(size + 1);
    }

    @Test
    @DisplayName("수강 신청은 모집 중, 진행 중이면 해당 인원이 추가 된다.")
    void apply_recruit_ongoing_success() {
        session = SessionFixtures.createdChargedSession(SessionRecruitStatus.RECRUIT, SessionProgressStatus.ONGOING);

        int size = session.applyCount();

        session.apply(
                NsUserFixtures.TEACHER_APPLE_3L,
                PaymentFixtures.payment(1L, 3L),
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(session.applyCount()).isEqualTo(size + 1);
    }

    @Test
    @DisplayName("수강 신청은 비 모집 중이면 신청할 수 없다는 예외를 반환 한다.")
    void apply_notRecruitStatus_throwsException() {
        session = SessionFixtures.createdChargedSession(SessionRecruitStatus.NOT_RECRUIT, SessionProgressStatus.ONGOING);

        assertThatThrownBy(
                () -> session.apply(
                        NsUserFixtures.TEACHER_APPLE_3L,
                        PaymentFixtures.payment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 모집 중, 종료 라면 신청할 수 없다는 예외를 반환 한다.")
    void apply_recruitStatus_endStatus_throwsException() {
        session = SessionFixtures.createdChargedSession(SessionRecruitStatus.RECRUIT, SessionProgressStatus.END);

        assertThatThrownBy(
                () -> session.apply(
                        NsUserFixtures.TEACHER_APPLE_3L,
                        PaymentFixtures.payment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의의 경우, 수강 인원 정원을 초과 하면 신청할 수 없다는 예외를 반환 한다.")
    void apply_chargeSession_overQuota_throwsException() {
        session = SessionFixtures.chargedSessionFullCanceled();

        assertThatThrownBy(
                () -> session.apply(
                        NsUserFixtures.TEACHER_APPLE_3L,
                        PaymentFixtures.payment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 유료 강의 결제가 안 되었다면 신청할 수 없다는 예외를 반환 한다.")
    void apply_chargeSession_notPaid_throwsException() {
        session = SessionFixtures.createdChargedSession();

        assertThatThrownBy(
                () -> session.apply(
                        NsUserFixtures.TEACHER_APPLE_3L,
                        null,
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("수강 신청은 수강 금액과 지불 금액이 다르면 신청할 수 없다는 예외를 던진다.")
    void apply_chargeSession_differentAmount_throwsException() {
        session = SessionFixtures.createdChargedSession();

        assertThatThrownBy(
                () -> session.apply(
                        NsUserFixtures.TEACHER_APPLE_3L,
                        PaymentFixtures.differentPayment(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnReady 는 변경할 날짜가 강의 종료일과 같거나 늦으면 예외를 던진다.")
    void changeOnReady_changeDateIsSameOrAfterWithEndDate_throwsException() {
        session = SessionFixtures.createdFreeSession();

        assertThatThrownBy(
                () -> session.changeOnReady(SessionFixtures.DATE_2023_12_10)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnReady(SessionFixtures.DATE_2023_12_12)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnGoing 는 변경할 날짜가 강의 종료일과 같거나 늦으면 예외를 던진다.")
    void changeOnGoing_changeDateIsSameOrAfterWithEndDate_throwsException() {
        session = SessionFixtures.createdFreeSession();

        assertThatThrownBy(
                () -> session.changeOnGoing(SessionFixtures.DATE_2023_12_10)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnGoing(SessionFixtures.DATE_2023_12_12)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("changeOnEnd 는 변경할 날짜가 강의 종료일보다 빠르거나 같다면 예외를 던진다.")
    void changeOnEnd_changeDateIsBeforeOrSameWithEndDate_throwsException() {
        session = SessionFixtures.createdFreeSession();

        assertThatThrownBy(
                () -> session.changeOnEnd(SessionFixtures.DATE_2023_12_6)
        ).isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(
                () -> session.changeOnEnd(SessionFixtures.DATE_2023_12_10)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("approve 는 선생님인 경우 수강생의 강의 신청을 승인한다.")
    void approve_teacher_changeApproveTrue() {
        session = SessionFixtures.chargedSessionFullCanceled();

        Apply changedApply = session.approve
                (NsUserFixtures.TEACHER_JAVAJIGI_1L,
                ApplyFixtures.apply_one_canceled(),
                SessionFixtures.DATETIME_2023_12_5
                );

        assertThat(changedApply.isApproved()).isTrue();
    }

    @Test
    @DisplayName("approve 는 학생인 경우 권한이 없다는 예외를 던진다.")
    void approve_student_throwsException() {
        session = SessionFixtures.chargedSessionFullCanceled();

        assertThatThrownBy(
                () -> session.approve(
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

        assertThatThrownBy(
                () -> session.approve(
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

        Apply changedApply = session.cancel(
                NsUserFixtures.TEACHER_JAVAJIGI_1L,
                ApplyFixtures.apply_one_canceled(),
                SessionFixtures.DATETIME_2023_12_5
        );

        assertThat(changedApply.isApproved()).isFalse();
    }

    @Test
    @DisplayName("cancel 는 학생인 경우 권한이 없다는 예외를 던진다.")
    void cancel_student_throwsException() {
        session = SessionFixtures.chargedSessionFullApproved();

        assertThatThrownBy(
                () -> session.cancel(
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

        assertThatThrownBy(
                () -> session.cancel(
                        NsUserFixtures.TEACHER_JAVAJIGI_1L,
                        ApplyFixtures.apply_one_canceled(),
                        SessionFixtures.DATETIME_2023_12_5
                )
        ).isInstanceOf(IllegalArgumentException.class);
    }
}
