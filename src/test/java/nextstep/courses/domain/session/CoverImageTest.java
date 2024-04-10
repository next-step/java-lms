package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {
    public static final CoverImage COVER_IMAGE_PNG = CoverImage.pngImage("이미지", 1024 * 1024, 300, 200);

    @Nested
    @DisplayName("CoverImage 생성 테스트")
    class InstanceCreationTest {
        @Nested
        @DisplayName("CoverImage 생성을 실패하는 경우를 테스트합니다.")
        class FailCaseTest {
            @Test
            @DisplayName("사이즈는 byte 단위로 입력되며 사이즈는 1MB 이하가 아닌 경우 IllegalArgumentException이 발생한다.")
            void testBigCapacity() {
                assertThatThrownBy(() -> CoverImage.gifImage("이미지", 1024 * 1024 + 10, 300, 200))
                        .isExactlyInstanceOf(IllegalArgumentException.class);
            }

            @ParameterizedTest
            @CsvSource(value = {"200:200", "300:100"}, delimiter = ':')
            @DisplayName("width는 300픽셀, height는 200픽셀 이상이어야 한다. 그렇지 않은 경우 IllegalArgumentException이 발생한다.")
            void testWrongWidthHeight(double width, double height) {
                assertThatThrownBy(() -> CoverImage.jpgImage("이미지", 1024 * 1024, width, height))
                        .isExactlyInstanceOf(IllegalArgumentException.class);
            }

            @Test
            @DisplayName("width와 height의 비율이 3:2가 아닌 경우 IllegalArgumentException이 발생한다.")
            void testWrongRatio() {
                assertThatThrownBy(() -> CoverImage.jpegImage("이미지", 1024 * 1024, 302, 201))
                        .isExactlyInstanceOf(IllegalArgumentException.class);
            }
        }

        @Test
        @DisplayName("검증 조건을 모두 만족하는 경우 어떠한 예외도 발생하지 않고 인스턴스가 생성된다.")
        void testSuccessCase() {
            assertThatNoException().isThrownBy(() -> CoverImage.pngImage("이미지", 1024 * 1024, 300, 200));
        }
    }
}