package nextstep.courses.domain.session;

import nextstep.courses.InvalidImageException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.SessionImageType.gif;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class SessionImageTypeTest {

    @Test
    @DisplayName("이름이 똑같은 확장자를 찾아 리턴한다.")
    void of() {
        assertEquals(SessionImageType.of("gif"), gif);
    }

    @Test
    @DisplayName("등록되지 않은 확장자를 넣으면 에러를 반환한다.")
    void of_fail() {
        assertThatThrownBy(() -> SessionImageType.of("xls"))
                .isInstanceOf(InvalidImageException.class);
    }
}