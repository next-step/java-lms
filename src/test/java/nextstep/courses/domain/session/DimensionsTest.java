package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DimensionsTest {
    @Nested
    @DisplayName("CoverImageWidthHeight 생성 테스트")
    class InstanceCreationTest {
        @Nested
        @DisplayName("CoverImageWidthHeight 생성을 실패하는 경우를 테스트")
        class FailCaseTest {
            @ParameterizedTest
            @CsvSource(value = {"200:200", "300:100"}, delimiter = ':')
            @DisplayName("width는 300픽셀, height는 200픽셀 이상이어야 한다. 그렇지 않은 경우 IllegalArgumentException이 발생한다.")
            void testWrongWidthHeight(double width, double height) {
                assertThatThrownBy(() -> new Dimensions(width, height))
                        .isExactlyInstanceOf(IllegalArgumentException.class);
            }

            @Test
            @DisplayName("width와 height의 비율이 3:2가 아닌 경우 IllegalArgumentException이 발생한다.")
            void testWrongRatio() {
                assertThatThrownBy(() -> new Dimensions(302, 201))
                        .isExactlyInstanceOf(IllegalArgumentException.class);
            }
        }

        @Test
        @DisplayName("검증 조건을 모두 만족하는 경우 어떠한 예외도 발생하지 않고 인스턴스가 생성된다.")
        void testSuccessCase() {
            assertThatNoException().isThrownBy(() -> new Dimensions(300, 200));
        }
    }
}