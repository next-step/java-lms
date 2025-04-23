package nextstep.courses.domain.session.image;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static nextstep.courses.domain.session.image.SessionImageType.JPEG;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionImageTest {
    private static final String testImageUrl = "https://test.com";

    @DisplayName("SessionImage 인스턴스 생성")
    @Test
    public void testConstructor() throws IOException {
        int width = 300;
        int height = 200;
        long byteSize = 1024L * 866L;

        assertDoesNotThrow(() -> new SessionImage("1", false, testImageUrl, JPEG) {
            @Override
            public int height() {
                return height;
            }

            @Override
            public int width() {
                return width;
            }

            @Override
            public long byteSize() {
                return byteSize;
            }
        });
    }

    @DisplayName("SessionImage 인스턴스 생성 - width와 height의 비율은 3:2 가 아니면 예외를 던짐")
    @Test
    public void testImage_throwExceptionByRatio() {
        int width = 300;
        int height = 201;
        long byteSize = 1024L * 866L;

        assertThatThrownBy(() -> new SessionImage("1", false, testImageUrl, JPEG) {
            @Override
            public int height() {
                return height;
            }

            @Override
            public int width() {
                return width;
            }

            @Override
            public long byteSize() {
                return byteSize;
            }
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("width와 height의 비율은 3:2 이여야 합니다.");
    }

    @DisplayName("이미지 가져오기 - 크기가 1MB를 초과하면 예외를 던짐")
    @Test
    public void testImage_throwExceptionBySize() {
        int width = 300;
        int height = 200;
        long byteSize = 1024L * 1025L;

        assertThatThrownBy(() -> new SessionImage("1", false, testImageUrl, JPEG) {
            @Override
            public int height() {
                return height;
            }

            @Override
            public int width() {
                return width;
            }

            @Override
            public long byteSize() {
                return byteSize;
            }
        })
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("크기가 1MB를 초과했습니다.");
    }
}
