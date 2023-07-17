package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class EnrollmentTest {
    private static final NsUser jerry = new NsUser(1L, "jerry", "password", "name", "jerry@nextstep.com");

    @Test
    void 생성자테스트() {
        assertThat(new Enrollment(1L, jerry)).isInstanceOf(Enrollment.class);
    }

    @Test
    void 수강승인() {
        Enrollment enrollment = new Enrollment(1L, jerry);

        enrollment.approve();

        assertThat(enrollment.isApproved()).isTrue();
    }

    @Test
    void 수강취소() {
        Enrollment enrollment = new Enrollment(1L, jerry);

        enrollment.cancel();

        assertThat(enrollment.isApproved()).isFalse();
    }
}
