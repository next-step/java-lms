package nextstep.courses.domian;

import nextstep.courses.InvalidImageNameException;
import nextstep.courses.domain.CoverImageFileName;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CoverImageFileNameTest {

    public static final CoverImageFileName CF = new CoverImageFileName("/test/test.jpg");

    @Test
    @DisplayName("정상적인 확장자와 이미지 경로를 입력한다면 정상 생성된다.")
    void createCoverImageFilePath() {
        CoverImageFileName coverImageFileName = new CoverImageFileName("test.jpg");

        assertThat(coverImageFileName).isEqualTo(new CoverImageFileName("test.jpg"));
    }

    @ParameterizedTest(name = "비어있거나, null인 이미지 경로를 입력하면 오류가 발생한다.")
    @NullAndEmptySource
    void createCoverImageFilePath_null_empty(String filePath) {
        assertThatThrownBy(() -> new CoverImageFileName(filePath))
                .isInstanceOf(InvalidImageNameException.class)
                .hasMessage("이미지 이름은 필수 값입니다.");
    }

    @ParameterizedTest(name = "확장자가 이미지파일 확장자(jpeg, jpg, png, gif)가 아닌 {0}라면 오류가 발생한다.")
    @ValueSource(strings = {"txt", "docs", "ppt"})
    void createCoverImageFilePath_not_image_extension(String filePath) {
        assertThatThrownBy(() -> new CoverImageFileName(filePath))
                .isInstanceOf(InvalidImageNameException.class)
                .hasMessage("이미지 파일 형식이 아닙니다.");
    }
}
