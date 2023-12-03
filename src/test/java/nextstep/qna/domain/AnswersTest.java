package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.QuestionTest.답변_있는_질문;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    @Test
    @DisplayName("답변이 있는데 질문을 삭제하면 예외를 발생한다.")
    void 질문_삭제시_답변_모두_삭제() throws CannotDeleteException {
        assertThatThrownBy(() -> 답변_있는_질문.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);

    }

}
