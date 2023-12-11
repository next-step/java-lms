package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDate;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class EnrollmentTest {

    @Test
    void 수강_신청은_모집중일때만_가능하다() {
        // given
        Period period = new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2024, 12, 1));
        Enrollment enrollment = new Enrollment(period, SessionStatus.PREPARING);

        // when, then
        assertThatThrownBy(
            () -> enrollment.enroll(NsUserTest.JAVAJIGI)).isInstanceOf(
            IllegalArgumentException.class).hasMessageContaining("강의 수강신청은 강의 상태가 모집중일 때만 가능합니다.");
    }

    @Test
    void 수강_신청() {
        // given
        Period period = new Period(LocalDate.of(2023, 12, 1), LocalDate.of(2024, 12, 1));
        Enrollment enrollment = new Enrollment(period, SessionStatus.RECRUITING);

        // when
        enrollment.enroll(NsUserTest.JAVAJIGI);

        // then
        assertThat(enrollment.getStudents()).hasSize(1);
    }

}
