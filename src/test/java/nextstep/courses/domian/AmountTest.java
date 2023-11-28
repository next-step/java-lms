package nextstep.courses.domian;

import nextstep.courses.domain.Amount;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    @Test
    @DisplayName("만일 금액생성시 금액 값이 null이라면 오류가 발생한다.")
    void createSessionAmount_null() {
        Assertions.assertThatThrownBy(() -> new Amount(null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 비어있을 수 없습니다.");
    }

    @Test
    @DisplayName("만일 금액생성시 금액 값이 음수라면 오류가 발생한다.")
    void createSessionAmount_negative() {
        Assertions.assertThatThrownBy(() -> new Amount(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다.");
    }

    @ParameterizedTest(name = "만일 입력된 금액({0})이 현재 강의의 금액(30_000)과 같은지 비교한다면 {1}를 반환한다.")
    @CsvSource(value = {"30_000, true", "20_000, false"})
    void isCorrectAmount(Long userPayed, boolean expected) {
        Amount amount = new Amount(30_000L);

        assertThat(amount.isCorrectAmount(userPayed)).isEqualTo(expected);
    }
}
