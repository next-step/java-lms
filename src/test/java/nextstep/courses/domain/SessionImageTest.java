package nextstep.courses.domain;

import nextstep.courses.constant.ImageFileType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionImageTest {
    public static final SessionImage SESSION_IMAGE = new SessionImage(1, ImageFileType.JPG, 300, 200);
    @Test
    @DisplayName("SessionImage 검증 테스트 - 이미지 용량 초과")
    public void validateSessionImageOverSizeTest() {
        SessionImage sessionImage = new SessionImage(2, ImageFileType.GIF, 300, 200);

        assertThatThrownBy(sessionImage::validateSessionImage)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 크기는 1MB 이하여야 합니다.");
    }

    @Test
    @DisplayName("SessionImage 검증 테스트 - 이미지 용량 초과")
    public void validateSessionImageUnderWidthOrHeightTest() {
        SessionImage sessionImage = new SessionImage(1, ImageFileType.GIF, 240, 160);

        assertThatThrownBy(sessionImage::validateSessionImage)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 해상도는 가로 300px, 세로 200px 이상이어야 합니다.");

        sessionImage = new SessionImage(1, ImageFileType.GIF, 320, 160);

        assertThatThrownBy(sessionImage::validateSessionImage)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 해상도는 가로 300px, 세로 200px 이상이어야 합니다.");

        sessionImage = new SessionImage(1, ImageFileType.GIF, 240, 240);

        assertThatThrownBy(sessionImage::validateSessionImage)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지 해상도는 가로 300px, 세로 200px 이상이어야 합니다.");
    }

    @Test
    @DisplayName("SessionImage 검증 테스트 - 이미지 용량 초과")
    public void validateSessionImageRatioErrorTest() {
        SessionImage sessionImage = new SessionImage(1, ImageFileType.GIF, 300, 201);

        assertThatThrownBy(sessionImage::validateSessionImage)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미지의 가로:세로 비율은 3:2 여야 합니다.");
    }

    @Test
    @DisplayName("SessionImage 검증 테스트 - 유효하지 않은 확장자 파일 입력")
    public void invalidImageFileTypeTest() {
        assertThatThrownBy(() -> new SessionImage(1, "bmp", 300, 200))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("bmp은 유효한 이미지 파일이 아닙니다. (허용 타입: gif, jpg, jpeg, png, svg)");
    }
}
