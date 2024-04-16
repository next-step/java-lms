package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionImageTest {

    private static final Long VALID_ID = 1L;
    private static final int VALID_HEIGHT = 200;
    private static final int VALID_WIDTH = 300;
    private static final int VALID_SIZE = 1024 * 1024;
    private static final String VALID_PATH = "image.png";
    public static final SessionImage S1 = new SessionImage(VALID_ID, VALID_PATH, VALID_WIDTH, VALID_HEIGHT, VALID_SIZE);
    public static final SessionImage S2 = new SessionImage(VALID_ID+1, VALID_PATH, VALID_WIDTH, VALID_HEIGHT, VALID_SIZE);


    @Test
    @DisplayName("강의 이미지 생성 테스트")
    void testSessionImage() {
        SessionImage sessionImage = new SessionImage(VALID_ID, VALID_PATH, VALID_WIDTH, VALID_HEIGHT, VALID_SIZE);

        assertThat(sessionImage.getId()).isEqualTo(VALID_ID);
        assertThat(sessionImage.getPath()).isEqualTo(VALID_PATH);
    }
}
