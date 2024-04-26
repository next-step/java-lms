package nextstep.courses.domain;

import nextstep.courses.domain.image.FileExtension;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class FileExtensionTest {

    public static final FileExtension file_extension = new FileExtension("jpg");

    @Test
    void 이미지의_타입은_정해진_확장자_타입만을_사용한다() {
        assertThatCode(() -> new FileExtension("jpg")).doesNotThrowAnyException();
        assertThatIllegalArgumentException().isThrownBy(() -> new FileExtension("csv"));
    }
}