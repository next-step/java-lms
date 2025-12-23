package nextstep.courses.cohort.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

class CohortStudentCountTest {

    @Test
    void 기수의_수강가능인원이_1명미만이면_예외처리_할_수_있다() {
        assertThatThrownBy(
                () -> new CohortStudentCount(0, 1)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 기수의_현재수강인원이_0명미만이면_예외처리_할_수_있다() {
        assertThatThrownBy(
                () -> new CohortStudentCount(1, -1)
        ).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 현재수강인원이_수강가능인원보다_적은상태인것을_확인_할_수_있다() {
        CohortStudentCount cohortStudentCount = new CohortStudentCount(10, 9);

        assertThat(cohortStudentCount.isNotOverMax()).isTrue();
    }

    @Test
    void 현재수강인원을_1명_늘릴수_있다() {
        CohortStudentCount cohortStudentCount = new CohortStudentCount(10, 9);

        assertThat(cohortStudentCount.isNotOverMax()).isTrue();
        cohortStudentCount.plusOneCountAtPresent();

        assertThat(cohortStudentCount.isNotOverMax()).isFalse();
    }
}