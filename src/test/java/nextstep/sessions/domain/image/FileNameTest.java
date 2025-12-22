package nextstep.sessions.domain.image;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class FileNameTest {

    @Test
    void whenFileNameIsEmpty_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new FileName("")
        ).withMessageContaining("파일명");
    }

    @Test
    void extension() {
        assertThat(new FileName("testImage.jpg").extension()).isEqualTo("jpg");
    }
}