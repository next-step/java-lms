package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {

    @Test
    @DisplayName("성공적으로 답변들을 삭제한다")
    void delete() throws CannotDeleteException {
        // given
        List<Answer> answersList = List.of(AnswerTest.A1);
        Answers answers = new Answers(answersList);

        // when
        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(deleteHistories).hasSize(1);
    }
}
