package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class EnrollmentTest {
    private SessionUsers sessionUsers;
    private SessionUser approvedUser;
    private SessionUser notApprovedUser;
    private Enrollment enrollment;

    @BeforeEach
    void setUp() {
        sessionUsers = new SessionUsers(10);
        approvedUser = new SessionUser(NsUserTest.JAVAJIGI.getId());
        notApprovedUser = new SessionUser(NsUserTest.SANJIGI.getId());
        enrollment = new Enrollment(sessionUsers, new SessionStatus(SessionProgressStatus.PROGRESSING, SessionRecruitmentStatus.RECRUITING));

        enrollment.addApprovedUser(NsUserTest.JAVAJIGI.getId());
    }

    @Test
    @DisplayName("수강신청 승인 시 선발인원에 존재 여부를 확인한다")
    void approve() {
        enrollment.enroll(approvedUser);
        enrollment.approve(approvedUser);

        assertThat(enrollment.enrollmentCount()).isEqualTo(1);
    }

    @Test
    @DisplayName("수강신청 거절 시 선발인원에 존재하지 않는지 확인한다")
    void reject() {
        enrollment.enroll(notApprovedUser);
        enrollment.reject(notApprovedUser);

        assertThat(enrollment.enrollmentCount()).isEqualTo(0);
    }

    @Test
    @DisplayName("수강신청 승인 시 선발인원에 존재하지 않으면 예외")
    void approve_fail() {
        enrollment.enroll(notApprovedUser);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> enrollment.approve(notApprovedUser))
                .withMessageMatching(Enrollment.VALIDATE_NOT_APPROVED_USER);
    }

    @Test
    @DisplayName("수강신청 거절 시 선발인원에 존재하면 예외")
    void reject_fail() {
        enrollment.enroll(approvedUser);

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> enrollment.reject(approvedUser))
                .withMessageMatching(Enrollment.VALIDATE_APPROVED_USER);
    }

}
