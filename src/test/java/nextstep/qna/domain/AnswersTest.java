package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswersTest {

    @Test
    void 모든_답변을_삭제한다() throws Exception {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A3));

        answers.deleteAll(NsUserTest.JAVAJIGI);

        for (Answer answer : answers.value()) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }

    @Test
    void 답변_중_하나라도_삭제가_불가능하면_예외가_발생한다() {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

        assertThatThrownBy(() -> answers.deleteAll(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
