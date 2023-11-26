package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AnswersTest {
    @Test
    @DisplayName("답변목록을 삭제한다. 답변목록 이력을 반환한다")
    public void delete() throws CannotDeleteException {
        DeleteHistories deleteHistories = new DeleteHistories();
        Answers answers = new Answers(deleteHistories);
        answers.add(new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1"));
        answers.delete(NsUserTest.JAVAJIGI);

        Assertions.assertThat(deleteHistories.deleteHistories()).hasSize(1);
    }
}
