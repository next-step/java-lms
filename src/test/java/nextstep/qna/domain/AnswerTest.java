package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("다른 사람이 답변을 단 경우 삭제불가")
    @Test
    public void deletion_of_answer_throws_exception() {
        NsUser questionUser = NsUserTest.JAVAJIGI;
        Answers answers = new Answers();
        answers.add(A2);

        org.junit.jupiter.api.Assertions.assertThrows(CannotDeleteException.class, () -> {
            answers.deleteAll(questionUser);
        });
    }

    @DisplayName("질문자만 답변을 단 경우 삭제가능")
    @Test
    public void deletion_of_answer_succeed() throws CannotDeleteException {
        NsUser questionUser = NsUserTest.JAVAJIGI;
        Answers answers = new Answers();
        answers.add(A1);

        answers.deleteAll(questionUser);
        Assertions.assertTrue(answers.isAnswersDeleted());
    }
}
