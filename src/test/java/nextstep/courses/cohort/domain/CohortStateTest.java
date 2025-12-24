package nextstep.courses.cohort.domain;

import static java.time.LocalDateTime.now;
import static java.time.LocalDateTime.of;
import static nextstep.courses.cohort.domain.enumeration.CohortStateType.RECRUIT;
import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.cohort.domain.enumeration.CohortStateType;
import org.junit.jupiter.api.Test;

class CohortStateTest {

    @Test
    void 주어진상태와_기수의상태가_동일한지_확인_할_수_있다() {
        CohortState cohortState = new CohortState(RECRUIT, new Period(now(), now()),
                new Period(now(), now()));
        assertThat(cohortState.isSameState(RECRUIT)).isTrue();
    }

    @Test
    void 현재시점이_기수의_모집중_기간인지_확인_할_수_있다() {
        CohortState cohortState = new CohortState(
                RECRUIT,
                new Period(
                        of(2025, 1, 1, 0, 0, 0),
                        of(2025, 1, 10, 23, 59, 59)
                ),
                new Period(
                        of(2025, 1, 17, 0, 0, 0),
                        of(2025, 2, 25, 23, 59, 59)
                )
        );

        assertThat(
                cohortState.isInRecruitPeriod(of(2025, 1, 1, 0, 0, 1))
        ).isTrue();
    }

    @Test
    void 기수의_상태를_모집마감으로_변경할_수_있다() {
        CohortState cohortState = new CohortState(
                RECRUIT,
                new Period(
                        of(2025, 1, 1, 0, 0, 0),
                        of(2025, 1, 10, 23, 59, 59)
                ),
                new Period(
                        of(2025, 1, 17, 0, 0, 0),
                        of(2025, 2, 25, 23, 59, 59)
                )
        );

        cohortState.changeRecruitEnd();

        assertThat(cohortState.isSameState(CohortStateType.RECRUIT_END)).isTrue();
    }


}