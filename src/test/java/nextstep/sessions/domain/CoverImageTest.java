package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;

public class CoverImageTest {
    public static final CoverImage TEST_IMAGE = new CoverImage(1L, SessionTest.FREE_SESSION, "test.png", 300, 200, 1024 * 1024);

    @DisplayName("강의 커버 이미지는")
    @Nested
    class Describe_constructor {

        @DisplayName("조건에 부합할 경우 생성된다.")
        @Test
        void valid() {
            assertThatCode(() -> new CoverImage(0L, SessionTest.FREE_SESSION, "image.png", 303, 202, 1024 * 1024))
                    .doesNotThrowAnyException();
        }

        @DisplayName("1MB 이하의 크기를 가져야 한다.")
        @Test
        void size_under_1MB() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new CoverImage(0L, SessionTest.FREE_SESSION, "image.png", 300, 200, 1024 * 1025))
                    .withMessage("이미지 크기는 1MB 이히여야 합니다.");
        }

        @DisplayName("제한된 확장자만 가질 수 있으며,")
        @Nested
        class Describe_FileExtension {

            @DisplayName("gif, jpg, jpeg, png, svg 만이 허용된다.")
            @ParameterizedTest(name = "{0} 확장자는 허용된다.")
            @ValueSource(strings = {"gif", "jpg", "jpeg", "png", "svg"})
            void valid(String fileName) {
                assertThatCode(() -> new CoverImage(0L, SessionTest.FREE_SESSION, "test." + fileName, 300, 200, 1024 * 1024))
                        .doesNotThrowAnyException();
            }

            @DisplayName("그 외의 파일 명의 경우 Exception을 던진다.")
            @ParameterizedTest(name = "{0} 확장자나 잘못된 파일명은 허용되지 않는다.")
            @ValueSource(strings = {"fail.html", "fail.py", "fail.docs", "fail.xlsx"})
            @NullAndEmptySource
            void invalid(String fileName) {
                assertThatIllegalArgumentException()
                        .isThrownBy(() -> new CoverImage(0L, SessionTest.FREE_SESSION, fileName, 300, 200, 1024 * 1024))
                        .withMessage("지원하지 않는 확장자 입니다.");
            }

        }

        @DisplayName("너비는 최소 300 픽셀이어야 한다.")
        @Test
        void min_width_300() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new CoverImage(0L, SessionTest.FREE_SESSION, "a.png", 299, 200, 1024 * 1024))
                    .withMessage("이미지 너비는 300픽셀 이상이어야 합니다.");
        }

        @DisplayName("높이는 최소 200 픽셀이어야 한다.")
        @Test
        void min_height_200() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new CoverImage(0L, SessionTest.FREE_SESSION, "a.png", 300, 199, 1024 * 1024))
                    .withMessage("이미지 높이는 200픽셀 이상이어야 합니다.");
        }

        @DisplayName("너비:높이 비율은 3:2여야 한다.")
        @Test
        void valid_size_ratio() {
            assertThatIllegalArgumentException()
                    .isThrownBy(() -> new CoverImage(0L, SessionTest.FREE_SESSION, "a.png", 300, 201, 1024 * 1024))
                    .withMessage("이미지 비율이 적합하지 않습니다.");
        }
    }

}
