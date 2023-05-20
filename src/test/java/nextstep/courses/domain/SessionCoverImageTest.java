package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionCoverImageTest {
    @Test
    void 이미지_변경_테스트() {
        SessionCoverImage sessionCoverImage1 = new SessionCoverImage("/course/math/main.png");
        System.out.println("s: " + sessionCoverImage1);
        System.out.println("change: " + sessionCoverImage1.changeImage("/course/english/main.png"));

        Assertions.assertThat(sessionCoverImage1.changeImage("/course/english/main.png"))
                .isEqualTo(new SessionCoverImage("/course/english/main.png"));
    }
}