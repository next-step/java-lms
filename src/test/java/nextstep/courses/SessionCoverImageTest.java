package nextstep.courses;

import nextstep.courses.domain.SessionCoverImage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionCoverImageTest {

    @DisplayName("SessionCoverImage 생성")
    @Test
    void test01() {
        SessionCoverImage sessionCoverImage = new SessionCoverImage("http://nextstep.tdd");

        assertThat(sessionCoverImage).isEqualTo(new SessionCoverImage("http://nextstep.tdd"));
    }

    @DisplayName("url 형식 오류")
    @Test
    void test02() {
        assertThatThrownBy(() -> new SessionCoverImage("test.site.com")).isInstanceOf(IllegalArgumentException.class);
    }

}
