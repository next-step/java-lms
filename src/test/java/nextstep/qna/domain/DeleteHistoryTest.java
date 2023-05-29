package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class DeleteHistoryTest {

    @Test
    @DisplayName("기본생성자로 생성된 객체는 비어있는 객체로 간주한다.")
    void isEmptyTest() {
        assertThat(new DeleteHistory().isEmpty()).isTrue();
    }

}
