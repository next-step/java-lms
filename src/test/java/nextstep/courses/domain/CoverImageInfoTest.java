package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class CoverImageInfoTest {
    private static final int NORMAL_SIZE = 1000;
    private static final String NORMAL_TYPE = "JPG";
    private static final int NORMAL_WIDTH = 300;
    private static final int NORMAL_HEIGHT = 200;
    private static final String IMAGE_SIZE_OVER_MESSAGE = "이미지 크기는 1024KB(1MB) 이하여야 합니다.";
    private static final String IMAGE_TYPE_INVALID_MESSAGE = "이미지 타입은 gif, jpg(jpeg 포함), png, svg만 가능합니다.";
    private static final String IMAGE_WIDTH_UNDER_MESSAGE = "이미지의 너비는 300 픽셀 이상이어야 합니다.";
    private static final String IMAGE_HEIGHT_UNDER_MESSAGE = "이미지의 높이는 200 픽셀 이상이어야 합니다.";
    private static final String IMAGE_WRONG_WIDTH_HEIGHT_RATE_MESSAGE = "이미지의 너비와 높이는 3:2 비율이어야 합니다.";

    @Test
    @DisplayName("이미지 크기는 1024KB(1MB)를 초과하지 않아야 한다.")
    void create_image_size_exception() {
        int overSize = 1025;
        assertThatThrownBy(() -> new CoverImageInfo(overSize, NORMAL_TYPE, NORMAL_WIDTH, NORMAL_HEIGHT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(IMAGE_SIZE_OVER_MESSAGE);
    }

    @Test
    @DisplayName("매개변수의 이미지 타입이 ImageType에 해당해야 한다.")
    void create_image_type_exception() {
        String wrongImageType = "vvc";
        assertThatThrownBy(() -> new CoverImageInfo(NORMAL_SIZE, wrongImageType, NORMAL_WIDTH, NORMAL_HEIGHT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(IMAGE_TYPE_INVALID_MESSAGE);
        ;
    }

    @Test
    @DisplayName("width는 300 이상이어야 한다")
    void create_image_width_exception() {
        int wrongWidth = 299;
        assertThatThrownBy(() -> new CoverImageInfo(NORMAL_SIZE, NORMAL_TYPE, wrongWidth, NORMAL_HEIGHT))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(IMAGE_WIDTH_UNDER_MESSAGE);
    }

    @Test
    @DisplayName("height는 200 이상이어야 한다")
    void create_image_height_exception() {
        int wrongHeight = 199;
        assertThatThrownBy(() -> new CoverImageInfo(NORMAL_SIZE, NORMAL_TYPE, NORMAL_WIDTH, wrongHeight))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(IMAGE_HEIGHT_UNDER_MESSAGE);
    }

    @Test
    @DisplayName("width와 height는 3:2 비율이어야 한다")
    void create_image_rate_exception() {
        int wrongHeight = 250;
        assertThatThrownBy(() -> new CoverImageInfo(NORMAL_SIZE, NORMAL_TYPE, NORMAL_WIDTH, wrongHeight))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(IMAGE_WRONG_WIDTH_HEIGHT_RATE_MESSAGE);
    }
    @Test
    @DisplayName("모든 조건 만족 시 정상 생성")
    void create_success() {
        assertThatNoException().isThrownBy(() -> new CoverImageInfo(NORMAL_SIZE, NORMAL_TYPE, NORMAL_WIDTH, NORMAL_HEIGHT));
    }

}