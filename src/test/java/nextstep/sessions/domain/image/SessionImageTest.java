package nextstep.sessions.domain.image;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class SessionImageTest {

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
}
