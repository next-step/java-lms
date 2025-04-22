package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class SessionTest {
    private final Image validImage = new Image(500f, "png", "cdn.com", 600, 400);
    private final Images validImages = new Images(List.of(validImage));
    private final Member member = new Member(1L, "홍길동", "hong@example.com");

    @Test
    @DisplayName("모집중 상태의 무료 강의는 수강 신청 가능하다")
    void freeSession_joinable_whenRecruiting() {
        Session session = new Session(
                "무료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,             // tuition
                0,             // currentCount
                0,             // capacity (무제한이지만 그냥 0으로 둠)
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        assertThat(session.joinable(new Payment())).isTrue();
    }

    @Test
    @DisplayName("모집중이 아닌 무료 강의는 수강 신청이 불가능하다")
    void freeSession_notJoinable_whenNotRecruiting() {
        Session session = new Session(
                "무료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,
                0,
                0,
                validImages,
                SessionStatus.PREPARING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        assertThat(session.joinable(new Payment())).isFalse();
    }

    @Test
    @DisplayName("모집중이고 정원이 남았고 결제 금액이 일치하면 유료 강의 수강 신청 가능")
    void paidSession_joinable_whenRecruiting_underCapacity_andPaidCorrectly() {
        Session session = new Session(
                "유료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                10000L,   // tuition
                29,      // currentCount
                30,      // capacity
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new PaidJoinStrategy()
        );

        assertThat(session.joinable(new Payment(10000L))).isTrue();
    }

    @Test
    @DisplayName("모집중이어도 결제 금액이 일치하지 않으면 유료 강의 수강 신청 불가")
    void paidSession_notJoinable_whenWrongAmount() {
        Session session = new Session(
                "유료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                10000L,
                10,
                30,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new PaidJoinStrategy()
        );

        assertThat(session.joinable(new Payment(8000L))).isFalse();
    }

    @Test
    @DisplayName("모집중이어도 정원이 초과되면 유료 강의 수강 신청 불가")
    void paidSession_notJoinable_whenOverCapacity() {
        Session session = new Session(
                "유료 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                10000L,
                30,
                30,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new PaidJoinStrategy()
        );

        assertThat(session.joinable(new Payment(10000L))).isFalse();
    }

    @Test
    @DisplayName("강의 상태가 PREPARING이면 모집중이어도 수강 신청 불가")
    void notJoinable_whenLecturePreparing_evenIfRecruiting() {
        Session session = new Session(
                "강의 준비 중",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,
                0,
                0,
                validImages,
                SessionStatus.PREPARING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        assertThat(session.joinable(new Payment())).isFalse();
    }

    @Test
    @DisplayName("강의 상태가 CLOSED이면 모집중이어도 수강 신청 불가")
    void notJoinable_whenLectureClosed_evenIfRecruiting() {
        Session session = new Session(
                "종료된 강의",
                1,
                LocalDateTime.now().minusDays(10),
                LocalDateTime.now().minusDays(3),
                0L,
                0,
                0,
                validImages,
                SessionStatus.CLOSED,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        assertThat(session.joinable(new Payment())).isFalse();
    }

    @Test
    @DisplayName("모집 상태가 NOT_RECRUITING이면 강의가 Ongoing이어도 수강 신청 불가")
    void notJoinable_whenNotRecruiting() {
        Session session = new Session(
                "모집 비활성 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,
                0,
                0,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.NOT_RECRUITING,
                new FreeJoinStrategy()
        );

        assertThat(session.joinable(new Payment())).isFalse();
    }

    @Test
    @DisplayName("수강 조건을 만족하면 수강 신청이 PENDING 상태로 등록된다")
    void enroll_success_creates_pending_enrollment() {
        Session session = new Session("강의", 1,
                LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                0L, 0, 10,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        session.enroll(new Payment(), member);

        Enrollment enrollment = session.getEnrollments().findByMember(member);
        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.PENDING);
    }

    @Test
    @DisplayName("동일한 사용자가 중복 신청 시 예외 발생")
    void duplicate_enrollment_should_throw() {
        Session session = new Session("강의", 1,
                LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                0L, 0, 10,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        session.enroll(new Payment(), member);

        assertThatThrownBy(() -> session.enroll(new Payment(), member))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 수강 신청한 회원입니다.");
    }

    @Test
    @DisplayName("강사가 수강 신청을 승인하면 APPROVED 상태가 되고 정원이 증가한다")
    void approve_enrollment_increases_capacity() {
        Session session = new Session("강의", 1,
                LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                0L, 0, 10,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        session.enroll(new Payment(), member);
        session.approveEnrollment(member);

        Enrollment enrollment = session.getEnrollments().findByMember(member);
        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
        assertThat(session.getCurrentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("강사가 수강 신청을 거절하면 REJECTED 상태가 된다")
    void reject_enrollment_sets_rejected_status() {
        Session session = new Session("강의", 1,
                LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                0L, 0, 10,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        session.enroll(new Payment(), member);
        session.rejectEnrollment(member);

        Enrollment enrollment = session.getEnrollments().findByMember(member);
        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.REJECTED);
    }

    @Test
    @DisplayName("승인하지 않으면 수강 신청은 APPROVED 상태가 아니다")
    void pending_enrollment_not_approved_by_default() {
        Session session = new Session("강의", 1,
                LocalDateTime.now(), LocalDateTime.now().plusDays(7),
                0L, 0, 10,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );

        session.enroll(new Payment(), member);

        Enrollment enrollment = session.getEnrollments().findByMember(member);
        assertThat(enrollment.isApproved()).isFalse();
    }

}
