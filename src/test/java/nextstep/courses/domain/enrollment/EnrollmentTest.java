package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.session.FreeSessionType;
import nextstep.courses.domain.session.PaidSessionType;
import nextstep.courses.domain.session.RecruitmentStatus;
import nextstep.courses.domain.session.SessionType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class EnrollmentTest {
    @Test
    public void 모집중_상태일때_수강신청_가능() {
        Enrollment enrollment = new Enrollment(1L, RecruitmentStatus.RECRUITING, new FreeSessionType(), Collections.emptyList());

        EnrollmentCandidate candidate = enrollment.enroll(1L, null);

        assertThat(candidate.getNsUserId()).isEqualTo(1L);
        assertThat(candidate.getSessionId()).isEqualTo(1L);
    }

    @Test
    public void 준비중_상태일때_수강신청_불가() {
        Enrollment enrollment = new Enrollment(RecruitmentStatus.NOT_RECRUITING, new FreeSessionType());

        assertThatThrownBy(() -> {
            enrollment.enroll(1L, null);
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("모집중인 강의만 수강 신청할 수 있다");
    }

    @Test
    public void 유료_강의_결제금액_검증() {
        SessionType type = new PaidSessionType(10, 100_000L);
        Enrollment enrollment = new Enrollment(1L, RecruitmentStatus.RECRUITING, type, Collections.emptyList());

        assertThatThrownBy(() -> enrollment.enroll(1L, new Payment("결제번호-1", 1L, 1L, 50_000L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("결제 금액이 수강료와 일치하지 않습니다");
    }

    @Test
    public void 유료강의의_최대인원을_초과하면_예외() {
        long fee = 100_000L;
        SessionType type = new PaidSessionType(2, fee);

        List<EnrollmentCandidate> currentStudent = List.of(
                new EnrollmentCandidate(1L, 1L),
                new EnrollmentCandidate(1L, 2L)
        );

        Enrollment enrollment = new Enrollment(1L, RecruitmentStatus.RECRUITING, type, currentStudent);

        assertThatThrownBy(() -> {
            enrollment.enroll(3L, new Payment("결제번호-1", 1L, 3L, fee));
        }).isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("최대 수강 인원을 초과");
    }

    @Test
    public void 수강후보자_생성() {
        Enrollment enrollment = new Enrollment(1L, RecruitmentStatus.RECRUITING, new FreeSessionType(), Collections.emptyList());

        EnrollmentCandidate candidate = enrollment.apply(1L, null);

        assertThat(candidate.getSessionId()).isEqualTo(1L);
        assertThat(candidate.getNsUserId()).isEqualTo(1L);
        assertThat(candidate.getStatus()).isEqualTo(EnrollmentStatus.PENDING);
    }

    @Test
    public void 수강후보자_승인하여_수강생_등록() {
        Enrollment enrollment = new Enrollment(1L, RecruitmentStatus.RECRUITING, new FreeSessionType(), Collections.emptyList());
        EnrollmentCandidate candidate = enrollment.apply(1L, null);

        EnrollmentCandidate approved = enrollment.approve(candidate, 100L);

        assertThat(approved.getSessionId()).isEqualTo(1L);
        assertThat(approved.getNsUserId()).isEqualTo(1L);
        assertThat(candidate.getStatus()).isEqualTo(EnrollmentStatus.APPROVED);
    }
}
