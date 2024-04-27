package nextstep.courses.domain.session.enrollment.state;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class ProgressStateTest {

    @Test
    void RecruitmentState_준비중_key값_으로_생성_되어야_한다(){
        assertThat(ProgressState.valueOf("PREPARING")).isEqualTo(ProgressState.PREPARING);
    }

    @Test
    void RecruitmentState_모집중_key값_으로_생성_되어야_한다(){
        assertThat(ProgressState.valueOf("ONGOING")).isEqualTo(ProgressState.ONGOING);
    }

    @Test
    void RecruitmentState_대기중_key값_으로_생성_되어야_한다(){
        assertThat(ProgressState.valueOf("END")).isEqualTo(ProgressState.END);
    }
}
