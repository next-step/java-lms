package nextstep.courses.domain.enrollment;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exceptions.AlreadyEnrollmentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;

import static nextstep.courses.domain.fixture.StudentsFixture.수강신청_학생들;
import static nextstep.users.domain.NsUserFixture.HYUNGKI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class EnrollmentTest {

    @Test
    @DisplayName("수강신청 가능 태스트")
    void enrolment_success() throws AlreadyEnrollmentException {
        // given
        final Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 3, 수강신청_학생들);

        // when & then
        enrollment.enroll(HYUNGKI);
    }

    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "FINISH"})
    @DisplayName("수강신청 불가능 태스트")
    void enrolment_fail(SessionStatus sessionStatus) {
        // given
        final Enrollment enrollment = new Enrollment(sessionStatus, 3, 수강신청_학생들);

        // when & then
        assertThatThrownBy(() ->enrollment.enroll(HYUNGKI))
                .isInstanceOf(IllegalArgumentException.class);
    }

}