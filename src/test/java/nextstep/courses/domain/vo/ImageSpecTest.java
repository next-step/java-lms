package nextstep.courses.domain.vo;

import nextstep.courses.code.ImageType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ImageSpecTest {

    public static ImageSpec INFO = new ImageSpec(1024, 300, 200, ImageType.JPEG);;

    @Test
    @DisplayName("이미지 정보 실패 테스트 - 최대 크기 초과")
    void imageInfoFailForOverThanMaxSizeTest() {
        assertThatThrownBy(() -> {
            new ImageSpec(1025, 300, 200, ImageType.JPEG);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하이어야 합니다.");
    }

    @Test
    @DisplayName("이미지 정보 실패 테스트 - 최소 너비 미만")
    void imageInfoFailForShorterThanMinWidthTest() {
        assertThatThrownBy(() -> {
            new ImageSpec(1024, 299, 200, ImageType.JPEG);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 가로길이는 300픽셀 이상이어야 합니다.");
    }

    @Test
    @DisplayName("이미지 정보 실패 테스트 - 최소 높이 미만")
    void imageInfoFailForShorterThanMinHeightTest() {
        assertThatThrownBy(() -> {
            new ImageSpec(1024, 300, 199, ImageType.JPEG);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 세로길이는 200픽셀 이상이어야 합니다.");
    }

    @Test
    @DisplayName("이미지 정보 실패 테스트 - 잘못된 비율")
    void imageInfoFailForUnavailableRatioTest() {
        assertThatThrownBy(() -> {
            new ImageSpec(1024, 300, 210, ImageType.JPEG);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가로 세로 비율이 3:2를 만족해야 합니다.");
    }

    @Test
    @DisplayName("이미지 정보 실패 테스트 - 잘못된 확장자")
    void imageInfoFailForUnavailableExtensionTest() {
        assertThatThrownBy(() -> {
            new ImageSpec(1024, 300, 210, ".yml");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("잘못된 확장자 입니다.");
    }
}
