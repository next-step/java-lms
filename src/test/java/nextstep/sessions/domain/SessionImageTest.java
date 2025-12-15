package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;

class SessionImageTest {

    public static final SessionImage IMAGE = new SessionImage(
            "cover.png",
            500_000,
            300,
            200
    );

    @Test
    void createImage_success() {
        SessionImage image = new SessionImage(
                "cover.png",
                500_000,
                300,
                200
        );
        assertThat(image.fileName()).isEqualTo("cover.png");
    }

    @Test
    void whenFileNameIsEmpty_thenThrow() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                new SessionImage("", 100_000, 300, 200)
        ).withMessageContaining("파일명");
    }
}
