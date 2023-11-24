package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문을 작성한 사람이 아니라면 질문을 삭제할 수 없다.")
    void deleteQuestion_other_writer() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("삭제하려는 질문에 다른사람이 답변을 달 경우 삭제가 불가능하다.")
    void deleteQuestion_answer_other_writer() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(1L, NsUserTest.SANJIGI, question, "test"));

        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문을 작성한 사람의 질문글과, 답변글만 있다면 삭제가 가능하다.")
    void deleteQuestion_작성자() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(1L, NsUserTest.JAVAJIGI, question, "test"));

        assertThat(question.delete(NsUserTest.JAVAJIGI)).hasSize(2)
                .contains(DeleteHistory.createQuestionHistory(1L, NsUserTest.JAVAJIGI, LocalDateTime.now()))
                .contains(DeleteHistory.createQuestionHistory(1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
