package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionCoverImageTest {
    @Test
    @DisplayName("SessionCoverImage 생성")
    void test01() {
        SessionCoverImage sessionCoverImage = new SessionCoverImage("http://edu.nextstep.camp");

        assertThat(sessionCoverImage).isEqualTo(new SessionCoverImage("http://edu.nextstep.camp"));
    }
    @Test
    @DisplayName("url 형식이 맞지 않을 때 에러 발생")
    void test02() {
        assertThatThrownBy(() -> new SessionCoverImage("url.test.com")).isInstanceOf(IllegalArgumentException.class);
    }
}
