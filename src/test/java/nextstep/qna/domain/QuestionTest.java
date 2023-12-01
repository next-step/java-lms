package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("사용자와 질문자가 달라 질문을 삭제할 수 없다면 예외를 던진다.")
    void login_user_is_not_equal_to_NsUser() {
        // when // then
        assertThatThrownBy(() -> Q1.delete(SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문자와 답변자가 달라 질문을 삭제할 수 없다면 예외를 던진다.")
    void question_user_is_not_equal_to_answer_user() {
        // given
        Question question = new Question(JAVAJIGI, "질문", "질문입니다");
        question.addAnswer(new Answer(JAVAJIGI, question, "답변1"));
        question.addAnswer(new Answer(SANJIGI, question, "답변2"));

        // when // then
        assertThatThrownBy(() -> question.delete(JAVAJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
