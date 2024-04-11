package nextstep.courses.domain.session.coverImage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.ImageType.GIF;
import static nextstep.courses.domain.session.ImageType.PNG;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CoverImageTest {
    public static final CoverImage COVER_IMAGE_PNG = new CoverImage("이미지", 1024 * 1024, 300, 200, PNG);

    @Nested
    @DisplayName("CoverImage 생성 테스트")
    class InstanceCreationTest {
        @Nested
        @DisplayName("CoverImage 생성을 실패하는 경우를 테스트")
        class FailCaseTest {
            @Test
            @DisplayName("사이즈는 byte 단위로 입력되며 사이즈는 1MB 이하가 아닌 경우 IllegalArgumentException이 발생한다.")
            void testBigCapacity() {
                assertThatThrownBy(() -> new CoverImage("이미지", 1024 * 1024 + 10, 300, 200, GIF))
                        .isExactlyInstanceOf(IllegalArgumentException.class);
            }
        }

        @Test
        @DisplayName("검증 조건을 모두 만족하는 경우 어떠한 예외도 발생하지 않고 인스턴스가 생성된다.")
        void testSuccessCase() {
            assertThatNoException().isThrownBy(() -> new CoverImage("이미지", 1024 * 1024, 300, 200, PNG));
        }
    }
}
