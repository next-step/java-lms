package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.qna.domain.Question.INVALID_OWNER_EXCEPTION_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @DisplayName("질문의 작성자가 다르면 예외가 발생한다.")
    @Test
    void checkIfQuestionOwnerTest() {
        assertThatThrownBy(
                        () -> Q1.checkIfQuestionOwner(NsUserTest.SANJIGI)
                )
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage(INVALID_OWNER_EXCEPTION_MESSAGE);
    }

    @DisplayName("질문으로 질문과 그 답변글들의 DeleteHistory 를 생성할 수 있다.")
    @Test
    void generateDeleteHistoriesTest() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);
        List<DeleteHistory> deleteHistories = Q1.generateDeleteHistories();

        assertThat(deleteHistories.size()).isEqualTo(3);
    }

}
