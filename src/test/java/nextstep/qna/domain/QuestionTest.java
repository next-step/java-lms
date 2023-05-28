package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.JAVAJIGI_ANSWER;
import static nextstep.qna.domain.AnswerTest.SANJIGI_ANSWER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question JAVAJIGI_QUESTION = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    private Question test_question;

    @BeforeEach
    void init() {
        test_question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    }

    @Test
    @DisplayName("질문을 삭제할 권한이 없는경우 예외를 발생한다.")
    void checkIsOwnerTest() {
        assertThatThrownBy(() -> test_question.deleteQuestion(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문에 대해 다른사람의 답변이 있는경우 익셉션을 발생한다.")
    void checkHasOtherUserAnswerTest() {
        test_question.addAnswer(SANJIGI_ANSWER);
        assertThatThrownBy(() -> test_question.deleteQuestion(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문이 삭제될 경우 그 하위 등록된 답변또한 전체 삭제가 이뤄진다.")
    void deleteQuestionTest() {
        test_question.addAnswer(JAVAJIGI_ANSWER);
        test_question.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(test_question.deleteHistories()).hasSize(2);
    }

    @Test
    @DisplayName("질문이 삭제상태가 아니거나 답변중 삭제상태가 아닌 답변이 있는 경우 빈 배열을 리턴한다.")
    void emptyHistoryTest() {
        assertThat(test_question.deleteHistories())
                .hasSize(0);
    }

    @Test
    @DisplayName("질문 삭제 내역을 반환한다.")
    void deleteHistoriesTest() {
        test_question.addAnswer(JAVAJIGI_ANSWER);
        // 삭제
        test_question.deleteQuestion(NsUserTest.JAVAJIGI);

        assertThat(test_question.deleteHistories()).hasSize(2);
    }

}
