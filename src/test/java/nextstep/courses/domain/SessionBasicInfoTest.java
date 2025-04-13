package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionBasicInfoTest {
    @Test
    @DisplayName("강의의 기본 정보를 생성한다")
    void create() {
        SessionBasicInfo basicInfo = new SessionBasicInfo(
            "강의 제목",
            new SessionImage("image.jpg", 300, 200)
        );

        assertThat(basicInfo.hasTitle("강의 제목")).isTrue();
        assertThat(basicInfo.isImageValid()).isTrue();
    }
} 