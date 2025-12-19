package nextstep.courses.cohort.domain;

import static nextstep.courses.cohort.domain.fixture.CohortFixture.식별자를_전달받아_기수픽스처를_생성한다;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.Test;

class CohortsTest {

    @Test
    void 기수목록에서_기수식별자로_기수를_찾을수_있다() {
        Cohort target = 식별자를_전달받아_기수픽스처를_생성한다(1L);
        Cohorts cohorts = new Cohorts(List.of(
                target,
                식별자를_전달받아_기수픽스처를_생성한다(2L),
                식별자를_전달받아_기수픽스처를_생성한다(3L)
        ));

        assertThat(cohorts.findCohortById(1L).get()).isEqualTo(target);
    }

}