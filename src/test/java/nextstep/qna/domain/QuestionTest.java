package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.AnswerTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    void setUp() {
        Q1.addAnswer(A1);
        Q1.addAnswer(A2);

        Q2.addAnswer(A3);
        Q2.addAnswer(A4);
    }

    @Test
    @DisplayName("질문 작성자와 답변 작성자가 같으면 데이터를 삭제 상태로 변경한다")
    void delete_success() throws CannotDeleteException {
        Question question = Q2.delete(NsUserTest.SANJIGI);

        assertThat(question.isDeleted()).isTrue();
        for (Answer answer : question.getAnswers()) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }

    @Test
    @DisplayName("질문 작성자와 답변 작성자가 다르면 삭제할 수 없다는 예외를 던진다")
    void delete_different_questionWriter_AnswerWriter_throwsException() {
        assertThatThrownBy(
                () -> Q1.delete(NsUserTest.JAVAJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
