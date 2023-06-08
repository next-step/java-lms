package nextstep.courses.domain;

import nextstep.courses.exception.InvalidURLException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CoverImageTest {

    @Test
    public void 올바른_URL_형식이_아니면_예외가_난다() throws Exception {
        assertThatCode(() -> new CoverImage("https://valid.com/images/0"))
                .doesNotThrowAnyException();
        assertThatThrownBy(() -> new CoverImage("invalid"))
                .isInstanceOf(InvalidURLException.class);
    }
}