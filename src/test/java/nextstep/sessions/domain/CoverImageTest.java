package nextstep.sessions.domain;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.io.File;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageTest {


    @Test
    void from() throws IOException {
        CoverImage coverImage = CoverImage.of(new File("playground.jpeg"), 1L);

        assertThat(coverImage)
                .isEqualTo(new CoverImage(1L, 1200, 800, 340797, "playground.jpeg", "jpeg", "playground.jpeg"));
    }

    @Nested
    class CoverImageExceptionTest {
        @Test
        void 너비가_기준이하인경우_예외를던진다() {
            assertThatThrownBy(() -> new CoverImage(1L, 299, 200, 1024 * 1024, "이미지", "jpeg", "/images/이미지.jpeg"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 너비는 " + CoverImage.MIN_WIDTH + " pixel 입니다");
        }

        @Test
        void 높이가_기준이하인경우_예외를던진다() {
            assertThatThrownBy(() -> new CoverImage(1L, 300, 199, 1024 * 1024, "이미지", "jpeg", "/images/이미지.jpeg"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최소 높이는 " + CoverImage.MIN_HEIGHT + " pixel 입니다");
        }

        @Test
        void 최대파일크기_초과한경우_예외를던진다() {
            assertThatThrownBy(() -> new CoverImage(1L, 300, 200, 1024 * 1024 + 1, "이미지", "jpeg", "/images/이미지.jpeg"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("최대 파일 크기를 초과하였습니다");
        }

        @Test
        void 지원하지않는_확장자는_예외를던진다() {
            assertThatThrownBy(() -> new CoverImage(1L, 300, 200, 1024 * 1024, "이미지", "exe", "/images/이미지.jpeg"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("지원하지 않는 확장자 입니다");
        }

        @Test
        void 비율이맞지않으면_예외를던진다() {
            assertThatThrownBy(() -> new CoverImage(1L, 300, 600, 1024 * 1024, "이미지", "jpg", "/images/이미지.jpeg"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("비율이 적합하지 않습니다");
        }

        @ParameterizedTest
        @NullAndEmptySource
        void 파일명이_비어있으면_예외를던진다(String fileName) {
            assertThatThrownBy(() -> new CoverImage(1L, 300, 200, 1024 * 1024, fileName, "jpg", "/images/이미지.jpeg"))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("파일명이 비어있습니다");
        }

        @ParameterizedTest
        @NullAndEmptySource
        void 파일경로가_비어있으면_예외를던진다(String filePath) {
            assertThatThrownBy(() -> new CoverImage(1L, 300, 200, 1024 * 1024, "이미지", "jpg", filePath))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("파일 경로가 비어있습니다");
        }
    }
}
