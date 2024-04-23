package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class EnrollmentTest {

    public static Enrollment enrollment = new Enrollment(20);

    @Test
    void can_sign_up() {
        assertThat(enrollment.canEnroll()).isTrue();
    }

    @Test
    void limit_exist() {
        assertThat(enrollment.limitExist()).isTrue();
    }


    @Test
    void 강의_수강신청은_강의_상태가_모집중일_때만_가능하다() {
        enrollment.open();
        assertThat(enrollment.canRegister()).isTrue();
    }

    @Test
    void 강의_수강신청은_강의_상태가_대기중_종료일_때_불가하다() {
        enrollment.close();
        assertThat(enrollment.canRegister()).isFalse();
    }
}