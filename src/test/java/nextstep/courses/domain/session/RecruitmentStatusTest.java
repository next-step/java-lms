package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RecruitmentStatusTest {


    @DisplayName("모집중 상태 여부를 알 수 있다.")
    @Test
    void isNotInProgressTest() {
        assertThat(RecruitmentStatus.NOT_RECRUITING.isNotRecruiting()).isTrue();
        assertThat(RecruitmentStatus.RECRUITING.isNotRecruiting()).isFalse();
    }
}