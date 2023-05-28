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

    private NsUser sanjigi;

    private NsUser javajigi;

    private Question javajigi_question;

    private Answer javajigi_answer;

    private Answer sanjigi_answer;

    @BeforeEach
    void init() {
        sanjigi = new NsUserDummy().b_user;
        javajigi = new NsUserDummy().a_user;
        javajigi_question = new QuestionDummy().getA_user_question();
        javajigi_answer = new AnswerDummy().a_answer;
        sanjigi_answer = new AnswerDummy().b_answer;
    }

    @Test
    @DisplayName("질문을 삭제할 권한이 없는경우 예외를 발생한다.")
    void checkIsOwnerTest() {
        assertThatThrownBy(() -> javajigi_question.deleteQuestion(sanjigi))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문에 대해 다른사람의 답변이 있는경우 익셉션을 발생한다.")
    void checkHasOtherUserAnswerTest() {
        javajigi_question.addAnswer(sanjigi_answer);
        assertThatThrownBy(() -> javajigi_question.deleteQuestion(javajigi))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문이 삭제될 경우 그 하위 등록된 답변또한 전체 삭제가 이뤄진다.")
    void deleteQuestionTest() {
        javajigi_question.addAnswer(javajigi_answer);
        javajigi_question.deleteQuestion(javajigi);

        assertThat(javajigi_question.deleteHistories()).hasSize(2);
    }

    @Test
    @DisplayName("질문이 삭제상태가 아니거나 답변중 삭제상태가 아닌 답변이 있는 경우 빈 배열을 리턴한다.")
    void emptyHistoryTest() {
        assertThat(javajigi_question.deleteHistories())
                .hasSize(0);
    }

    @Test
    @DisplayName("질문 삭제 내역을 반환한다.")
    void deleteHistoriesTest() {
        javajigi_question.addAnswer(javajigi_answer);
        // 삭제
        javajigi_question.deleteQuestion(javajigi);

        assertThat(javajigi_question.deleteHistories()).hasSize(2);
    }

}
