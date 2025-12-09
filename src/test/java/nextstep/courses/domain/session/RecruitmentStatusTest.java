package nextstep.courses.domain.session;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class RecruitmentStatusTest {
    @Test
    void 각상태별_생성확인() {
        assertThat(RecruitmentStatus.NOT_RECRUITING.getValue()).isEqualTo("비모집중");
        assertThat(RecruitmentStatus.RECRUITING.getValue()).isEqualTo("모집중");
    }

    @Test
    void 잘못된_상태_문자열이면_예외() {
        assertThatThrownBy(() -> {
            RecruitmentStatus.from("준비중");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 모집 상태");
    }

    @ParameterizedTest
    @CsvSource({
            "NOT_RECRUITING, false",
            "RECRUITING, true"
    })
    void 상태별_수강신청_가능여부(RecruitmentStatus status, boolean canEnroll) {
        assertThat(status.canEnroll()).isEqualTo(canEnroll);
    }
}
