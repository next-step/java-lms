package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.User;
import nextstep.users.domain.UserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer ANSWER_BY_JAVAJIGI = new Answer(UserTest.JAVAJIGI, QuestionTest.QUESTION_BY_JAVAJIGI, "Answers Contents1");
    public static final Answer ANSWER_BY_SANJIGI = new Answer(UserTest.SANJIGI, QuestionTest.QUESTION_BY_SANJIGI, "Answers Contents2");

    @DisplayName("질문자가 아닌 다른 사람이 답글을 단 경우 삭제되지 않는다.")
    @Test
    public void deletion_of_answer_throws_exception() {
        User questionUser = UserTest.JAVAJIGI;
        Answers answers = new Answers();
        answers.add(AnswerTest.ANSWER_BY_JAVAJIGI);
        answers.add(AnswerTest.ANSWER_BY_SANJIGI);

        Assertions.assertThatThrownBy(
                        () -> answers.deleteAll(questionUser))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문자만 답글을 작성했다면 삭제된다.")
    @Test
    public void deletion_of_answer_succeed() throws CannotDeleteException {
        User questionUser = UserTest.JAVAJIGI;
        Answers answers = new Answers();
        answers.add(AnswerTest.ANSWER_BY_JAVAJIGI);

        answers.deleteAll(questionUser);

        Assertions.assertThat(answers.getCount()).isEqualTo(0);
    }
}
