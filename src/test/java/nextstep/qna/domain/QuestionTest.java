package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.SANJIGI_ANSWER;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question JAVAHIGI_QUESTION = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question SANJIGI_QUESTION = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문을 삭제할 권한이 없는경우 예외를 발생한다.")
    void checkIsOwnerTest() {
        assertThatThrownBy(() -> JAVAHIGI_QUESTION.deleteQuestion(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문에 대해 다른사람의 답변이 있는경우 익셉션을 발생한다.")
    void checkHasOtherUserAnswerTest() {
        JAVAHIGI_QUESTION.addAnswer(SANJIGI_ANSWER);
        assertThatThrownBy(() -> JAVAHIGI_QUESTION.deleteQuestion(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

    }
}
