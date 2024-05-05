package nextstep.courses.domain.session.enrollment.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RecruitmentStateTest {

    @Test
    void RecruitmentState_비모집중_key값_으로_생성_되어야_한다(){
        assertThat(RecruitmentState.valueOf("NON_RECRUITING")).isEqualTo(RecruitmentState.NON_RECRUITING);
    }

    @Test
    void RecruitmentState_모집중_key값_으로_생성_되어야_한다(){
        assertThat(RecruitmentState.valueOf("RECRUITING")).isEqualTo(RecruitmentState.RECRUITING);
    }
}
