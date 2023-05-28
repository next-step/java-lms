package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.dummy.answer.AnswerDummy;
import nextstep.dummy.answer.NsUserDummy;
import nextstep.dummy.answer.QuestionDummy;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {

    private NsUser a_user;
    private NsUser b_user;

    private Question a_user_question;

    private Answer a_user_answer;

    private Answer b_user_answer;

    @BeforeEach
    void init() {
        b_user = new NsUserDummy().b_user;
        a_user = new NsUserDummy().a_user;
        a_user_question = new QuestionDummy().a_user_question;
        a_user_answer = new AnswerDummy().a_answer;
        b_user_answer = new AnswerDummy().b_answer;
    }

    @Test
    @DisplayName("질문을 삭제할 권한이 없는경우 예외를 발생한다.")
    void checkIsOwnerTest() {
        assertThatThrownBy(() -> a_user_question.deleteQuestion(b_user))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문에 대해 다른사람의 답변이 있는경우 익셉션을 발생한다.")
    void checkHasOtherUserAnswerTest() {
        a_user_question.addAnswer(b_user_answer);
        assertThatThrownBy(() -> a_user_question.deleteQuestion(a_user))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문이 삭제될 경우 그 하위 등록된 답변또한 전체 삭제가 이뤄진다.")
    void deleteQuestionTest() {
        a_user_question.addAnswer(a_user_answer);
        a_user_question.deleteQuestion(a_user);

        assertThat(a_user_question.deleteHistories()).hasSize(2);
    }

    @Test
    @DisplayName("질문이 삭제상태가 아니거나 답변중 삭제상태가 아닌 답변이 있는 경우 빈 배열을 리턴한다.")
    void emptyHistoryTest() {
        assertThat(a_user_question.deleteHistories())
                .hasSize(0);
    }

    @Test
    @DisplayName("질문 삭제 내역을 반환한다.")
    void deleteHistoriesTest() {
        a_user_question.addAnswer(a_user_answer);
        // 삭제
        a_user_question.deleteQuestion(a_user);

        assertThat(a_user_question.deleteHistories()).hasSize(2);
    }

}
