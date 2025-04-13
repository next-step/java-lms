package nextstep.courses.domain.session;

import nextstep.courses.CannotEnrollException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EnrollmentTest {

    @Test
    @DisplayName("등록에 성공한다.")
    void enroll() {
        Enrollment enrollment = new Enrollment(10);
        enrollment.enroll(NsUserTest.JAVAJIGI);
        assertEquals(enrollment, new Enrollment(List.of(NsUserTest.JAVAJIGI), new SessionCapacity(10)));
    }

    @Test
    @DisplayName("인원 초과로 인해 등록에 실패한다.")
    void enroll_fail() {
        Enrollment enrollment = new Enrollment(1);
        enrollment.enroll(NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> enrollment.enroll(NsUserTest.SANJIGI))
                .isInstanceOf(CannotEnrollException.class);
    }
}