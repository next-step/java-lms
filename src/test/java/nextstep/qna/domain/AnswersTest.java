package nextstep.qna.domain;


import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    public static final Answers CAN_DELETED_ANSWERS = new Answers(List.of(AnswerTest.A1, AnswerTest.A1));
    public static final Answers NOT_DELETED_ANSWERS = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

    @Test
    @DisplayName("질문들을 전체 삭제 하여 전체 isDeleted 값은 True")
    void Answers_전체_삭제_확인() throws CannotDeleteException {
        Answers answers = CAN_DELETED_ANSWERS;

        answers.allDeleted(NsUserTest.JAVAJIGI);
        List<Answer> answersList = answers.getAnswers();
        assertThat(answersList).allMatch(Answer::isDeleted);
    }

    @Test
    @DisplayName("작성자가 다르면 삭제 하지 못하고 예외 발생")
    void Answers_삭제_예외_발생() {
        Answers answers = NOT_DELETED_ANSWERS;

        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);

    }
}
