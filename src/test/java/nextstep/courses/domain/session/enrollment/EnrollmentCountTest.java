package nextstep.courses.domain.session.enrollment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.domain.session.enrollment.count.FreeEnrollmentCount;
import nextstep.courses.domain.session.enrollment.count.MaxRegistrationCount;
import nextstep.courses.domain.session.enrollment.count.PaidEnrollmentCount;
import nextstep.courses.domain.session.enrollment.count.RegistrationCount;
import nextstep.courses.domain.session.enrollment.count.engine.EnrollmentCount;
import nextstep.courses.error.exception.MaxRegistrationExceededException;
import org.junit.jupiter.api.Test;

class EnrollmentCountTest {

    @Test
    void 무료강의_등록_인원이_최대_등록_인원보다_낮은_경우_등록_인원이_증가될_수_있다() {
        EnrollmentCount enrollmentCount = new FreeEnrollmentCount( new RegistrationCount(5));

        assertThat(enrollmentCount.isRegistrationWithinCapacity()).isTrue();
    }

    @Test
    void 무료강의_등록_인원이_최대_등록_인원보다_높은_경우_등록_인원이_증가될_수_없다() {
        EnrollmentCount enrollmentCount = new FreeEnrollmentCount( new RegistrationCount(5));

        assertThat(enrollmentCount.getMaxRegistrationCount()).isEqualTo(Integer.MAX_VALUE);
    }

    @Test
    void 무료강의_등록_인원이_오버플로우가_생겨_음수가_된_경우_예외가_발생한다() {
        EnrollmentCount enrollmentCount = new FreeEnrollmentCount( new RegistrationCount(Integer.MAX_VALUE));

        assertThatThrownBy(() -> enrollmentCount.addRegistrationCount())
            .isInstanceOf(MaxRegistrationExceededException.class)
            .hasMessage("최대 등록 인원수를 초과하였습니다. 현재 강의 등록 인원: -2,147,483,648");
    }

    @Test
    void 유료강의_등록_인원이_최대_등록_인원보다_낮은_경우_등록_인원이_증가될_수_있다() {
        EnrollmentCount enrollmentCount = new PaidEnrollmentCount( new RegistrationCount(9), new MaxRegistrationCount(10));

        assertThat(enrollmentCount.isRegistrationWithinCapacity()).isTrue();
    }

    @Test
    void 유료강의_등록_인원이_최대_등록_인원보다_높은_경우_등록_인원이_증가될_수_없다() {
        EnrollmentCount enrollmentCount = new PaidEnrollmentCount( new RegistrationCount(10), new MaxRegistrationCount(10));

        assertThat(enrollmentCount.isRegistrationWithinCapacity()).isFalse();
    }

}
