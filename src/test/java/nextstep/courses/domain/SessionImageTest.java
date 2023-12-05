package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNoException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionImageTest {

    private static final int NORMAL_IMAGE_CAPACITY = 1_000_000;
    private static final int NORMAL_IMAGE_WIDTH = 300;
    private static final int NORMAL_IMAGE_HEIGHT = 200;

    @DisplayName("강의 이미지 용량이 1000000 이하이면 정상")
    @Test
    void a() {
        int imageCapacity = 1_000_000;
        ImageExtension imageExtension = ImageExtension.JPEG;
        assertThatNoException().isThrownBy(() -> {
            new SessionImage(imageCapacity, imageExtension, NORMAL_IMAGE_WIDTH, NORMAL_IMAGE_HEIGHT);
        });
    }

    @DisplayName("강의 이미지 용량이 1MB 이상이면 예외")
    @Test
    void a1() {
        int imageCapacity = 100_000_000;
        ImageExtension imageExtension = ImageExtension.JPEG;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new SessionImage(imageCapacity, imageExtension, NORMAL_IMAGE_WIDTH, NORMAL_IMAGE_HEIGHT);
        });
    }

    @DisplayName("강의 이미지 크기는 300x200 이상이면 정상")
    @Test
    void b() {
        ImageExtension imageExtension = ImageExtension.JPEG;
        assertThatNoException().isThrownBy(() -> {
            new SessionImage(NORMAL_IMAGE_CAPACITY, imageExtension, 699, 499);
        });
    }

    @DisplayName("강의 이미지 크기는 300x200 이하이면 예외")
    @Test
    void b1() {
        ImageExtension imageExtension = ImageExtension.JPEG;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new SessionImage(NORMAL_IMAGE_CAPACITY, imageExtension, 299, 199);
        });
    }

    @DisplayName("강의 이미지 비율은 3:2가 정상")
    @Test
    void c() {
        ImageExtension imageExtension = ImageExtension.JPEG;
        assertThatNoException().isThrownBy(() -> {
            new SessionImage(NORMAL_IMAGE_CAPACITY, imageExtension, 300, 200);
        });
    }

    @DisplayName("강의 이미지 비율은 3:2가 아니면 예외")
    @Test
    void c1() {
        ImageExtension imageExtension = ImageExtension.JPEG;
        assertThatIllegalArgumentException().isThrownBy(() -> {
            new SessionImage(NORMAL_IMAGE_CAPACITY, imageExtension, 2, 200);
        });
    }

}
