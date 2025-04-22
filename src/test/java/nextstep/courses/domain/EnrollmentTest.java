package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnrollmentTest {

    private final Member member = new Member(1L, "홍길동", "hong@example.com");
    private final Image validImage = new Image(500f, "png", "cdn.com", 600, 400);
    private final Images validImages = new Images(List.of(validImage));

    private Session createDummySession() {
        return new Session(
                "더미 강의",
                1,
                LocalDateTime.now(),
                LocalDateTime.now().plusDays(7),
                0L,
                0,
                10,
                validImages,
                SessionStatus.ONGOING,
                RecruitmentStatus.RECRUITING,
                new FreeJoinStrategy()
        );
    }

    @Test
    @DisplayName("Enrollment는 생성 시 PENDING 상태이다")
    void created_enrollment_has_pending_status() {
        Session session = createDummySession();
        Enrollment enrollment = new Enrollment(member, session);

        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.PENDING);
    }

    @Test
    @DisplayName("Enrollment를 승인하면 APPROVED 상태가 된다")
    void approve_sets_status_to_approved() {
        Session session = createDummySession();
        Enrollment enrollment = new Enrollment(member, session);

        enrollment.approve();

        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }

    @Test
    @DisplayName("Enrollment를 거절하면 REJECTED 상태가 된다")
    void reject_sets_status_to_rejected() {
        Session session = createDummySession();
        Enrollment enrollment = new Enrollment(member, session);

        enrollment.reject();

        assertThat(enrollment.getStatus()).isEqualTo(EnrollmentStatus.REJECTED);
    }

    @Test
    @DisplayName("이미 승인된 Enrollment는 다시 승인할 수 없다")
    void approving_twice_should_throw() {
        Session session = createDummySession();
        Enrollment enrollment = new Enrollment(member, session);

        enrollment.approve();

        assertThatThrownBy(enrollment::approve)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("이미 승인된 수강 신청입니다.");
    }
}
