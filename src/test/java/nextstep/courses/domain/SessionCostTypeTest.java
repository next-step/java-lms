package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionCostTypeTest {

    @DisplayName("강의비용 유형 : 없는경우")
    @ParameterizedTest
    @ValueSource(strings = {"무로, 유로, 외상"})
    void 강의비용_없는유형(String costType) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionCostType.of(costType))
                .withMessageMatching("없는 강의비용 유형 입니다.");
    }

    @DisplayName("강의비용 유형 : 무료")
    @ParameterizedTest
    @ValueSource(strings = {"무료"})
    void 강의비용_무료(String costType) {
        assertThat(SessionCostType.of(costType)).isEqualTo(SessionCostType.FREE);
    }

    @DisplayName("강의비용 유형 : 유료")
    @ParameterizedTest
    @ValueSource(strings = {"유료"})
    void 강의비용_유료(String costType) {
        assertThat(SessionCostType.of(costType)).isEqualTo(SessionCostType.PAID);
    }

}
