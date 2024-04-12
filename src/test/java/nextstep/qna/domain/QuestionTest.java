package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.Answer.ANSWER_DELETE_ERORR_MESSAGE;
import static nextstep.qna.domain.Question.QUESTION_DELETE_ERORR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문 삭제 기능 테스트")
    @Test
    void answer_delete_test() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();

    }

    @DisplayName("질문 삭제 에러 테스트")
    @Test
    void answer_delete_error_test(){
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).hasMessageContaining(QUESTION_DELETE_ERORR_MESSAGE);
    }

    @DisplayName("답변 포함 삭제 에러 테스트")
    @Test
    void delete_with_answer_error_test(){
        assertThatThrownBy(() -> {
            Question Q3 = new Question(NsUserTest.JAVAJIGI, "title3", "contents3");
            Q3.addAnswer(AnswerTest.A1);
            Q3.addAnswer(AnswerTest.A2);
            Q3.delete(NsUserTest.JAVAJIGI);
        }).hasMessageContaining(ANSWER_DELETE_ERORR_MESSAGE);
    }
}
