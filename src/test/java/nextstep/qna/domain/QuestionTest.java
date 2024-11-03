package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.Question.INVALID_OWNER_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @DisplayName("질문 삭제 시 작성자가 다르면 예외가 발생한다.")
    @Test
    void deleteQuestionTest() {
        assertThatThrownBy(
                () -> Q1.delete(NsUserTest.SANJIGI)
        )
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(INVALID_OWNER_EXCEPTION_MESSAGE);
    }

    @DisplayName("질문을 삭제하면 답변글도 함께 삭제되며 DeleteHistory 리스트를 반환한다.")
    @Test
    void answerDeleteTest() throws CannotDeleteException {
        Q1.addAnswer(A1);
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories.size()).isEqualTo(2);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
    }

}
