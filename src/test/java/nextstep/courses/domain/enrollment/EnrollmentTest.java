package nextstep.courses.domain.enrollment;

import nextstep.courses.enums.SessionStatus;
import nextstep.courses.exceptions.AlreadyEnrollmentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.EnumSource;

import java.util.ArrayList;
import java.util.Arrays;

import static nextstep.users.domain.NsUserFixture.HYUNGKI;
import static nextstep.users.domain.NsUserFixture.JAVAJIGI;
import static nextstep.users.domain.NsUserFixture.SANJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class EnrollmentTest {

    @Test
    @DisplayName("수강신청 가능 태스트")
    void enrolment_success() throws AlreadyEnrollmentException {
        // given
        final Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 3);

        // when & then
        enrollment.enroll(HYUNGKI, new ArrayList<>(Arrays.asList(JAVAJIGI, SANJIGI)));
    }

    @EnumSource(value = SessionStatus.class, names = {"PREPARING", "FINISH"})
    @DisplayName("수강신청 불가능 태스트")
    void enrolment_fail(SessionStatus sessionStatus) {
        // given
        final Enrollment enrollment = new Enrollment(sessionStatus, 3);

        // when & then
        assertThatThrownBy(() ->enrollment.enroll(HYUNGKI, new ArrayList<>(Arrays.asList(JAVAJIGI, SANJIGI))))
                .isInstanceOf(IllegalArgumentException.class);
    }

}