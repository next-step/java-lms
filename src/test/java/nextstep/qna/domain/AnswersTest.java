package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    void 작성자가_요청자와_다른_답변이_있으면_삭제할_수_없다() {
        Answers answers = new Answers(...);
        assertThatThrownBy(() -> answers.deleteAll())
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사용자의 답변이 존재합니다.");

    }
}
