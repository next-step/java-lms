package nextstep.courses.domain.session;

import nextstep.courses.exception.InvalidUrlPatternException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionCoverImageTest {

    @Test
    @DisplayName("이미지 URL을 가진다.")
    void test01() {
        String expected = "http://test.com/image01";
        SessionCoverImage coverImage = SessionCoverImage.create(expected);
        assertThat(coverImage.getImageUrl()).isEqualTo(expected);
    }

    @Test
    @DisplayName("커버이미지 생성시 정상적인 URL 패턴이 아니면 예외가 발생한다.")
    void test02() {
        assertThatThrownBy(() -> SessionCoverImage.create("file://test.com/image01"))
                .isInstanceOf(InvalidUrlPatternException.class);
    }

}
