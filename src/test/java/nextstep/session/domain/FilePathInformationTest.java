package nextstep.session.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FilePathInformationTest {

    @DisplayName("완벽한 파일 경로를 얻어올 수 있다.")
    @Test
    void getFullFilePath() {
        // given
        FilePathInformation filePathInformation =
                new FilePathInformation("/home/program/", "image01", "jpg");

        // then
        assertThat(filePathInformation.getFullFilePath())
                .isEqualTo(Path.of("/home/program/image01.jpg"));
    }

    @DisplayName("처리하지 않는 확장자를 사용하면 IllegalArgumentException을 던진다.")
    @Test
    void throwIllegalArgumentExceptionWhenNotInImageExtensionType() {
        // then
        assertThatThrownBy(() -> new FilePathInformation("/home/program/", "image01", "exe"))
                .isInstanceOf(IllegalArgumentException.class);
    }
}
