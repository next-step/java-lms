package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    public static Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    @DisplayName("작성자가 질문을 삭제하면 deleted 상태는 TRUE")
    void delete() {
        Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른 사람이 삭제를 요청하면 예외를 발생시킨다")
    void deleteNotOwner() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 있으면 삭제가 불가능하다.")
    void deleteHasAnswer() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("질문자와 답변자의 작성자가 모두 같다면 삭제 가능하다")
    void deleteAllSameWriter() {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문을 삭제할 때 답변도 삭제해야 한다.")
    void deleteQuestionAndAnswers() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문자와 답변자가 다를 경우 답변을 삭제할 수 없다.")
    void canNotDeleteQuestionerAndAnswererDiff() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제에 성공하면 삭제이력이 남는다.")
    void deleteHistory() {
        Q1.addAnswer(AnswerTest.A1);
        List<DeleteHistory> actual = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(actual).hasSize(2);
    }
}
