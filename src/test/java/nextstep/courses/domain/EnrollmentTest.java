package nextstep.courses.domain;

import nextstep.sessions.domain.Enrollment;
import nextstep.sessions.domain.SessionStatus;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class EnrollmentTest {
    @DisplayName("유료 강의는 강의 최대 수강 인원을 초과할 수 없다")
    @Test
    void paidLectureCannotOverMaximumAttendees() {
        // given & when & then
        Enrollment enrollment = new Enrollment(SessionStatus.OPENED, 1);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    enrollment.enroll(NsUserTest.JAVAJIGI);
                    enrollment.enroll(NsUserTest.SANJIGI);
                }).withMessageMatching("최대 수용 인원인 1명을 초과할 수 없습니다.");

    }

    @DisplayName("수강신청 상태가 아닐 때는 수강신청이 되지 않는다.")
    @Test
    void notCreateEnrollmentWithNotSessionOpened() {
        Enrollment enrollment = new Enrollment(SessionStatus.CLOSED, 10);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    enrollment.enroll(NsUserTest.JAVAJIGI);
                }).withMessageMatching("수강신청 상태가 아니라 수강신청할 수 없습니다.");
    }

    @DisplayName("수강신청을 이미 한 학생은 수강신청이 되지 않는다.")
    @Test
    void notCreateEnrollmentWithDuplicateStudent() {
        Enrollment enrollment = new Enrollment(SessionStatus.OPENED, 10);
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    enrollment.enroll(NsUserTest.JAVAJIGI);
                    enrollment.enroll(NsUserTest.JAVAJIGI);
                }).withMessageMatching("name는 이미 수강 신청한 학생입니다.");
    }
}
