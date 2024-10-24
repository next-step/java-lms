package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.qna.ForbiddenException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DeleteHistoryTest {

    @DisplayName("삭제된 Answer 객체를 기반으로 생성이 잘 되는지")
    @Test
    void createTest_byAnswer() {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "writer");
        answer.delete(NsUserTest.JAVAJIGI);
        DeleteHistory deleteHistory = new DeleteHistory(answer);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("삭제되지 않은 Answer 객체를 기반으로 생성하면 예외가 발생하는지")
    @Test
    void createTest_byNotDeletedAnswer() {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "writer");
        assertThatThrownBy(() -> new DeleteHistory(answer))
                .isInstanceOf(ForbiddenException.class);
    }

    @DisplayName("삭제된 Question 객체를 기반으로 생성이 잘 되는지")
    @Test
    void createTest_byQuestion() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        question.delete(NsUserTest.JAVAJIGI);
        DeleteHistory deleteHistory = new DeleteHistory(question);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("삭제되지 않은 Question 객체를 기반으로 생성하면 예외가 발생하는지")
    @Test
    void createTest_byNotDeletedQuestion() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        assertThatThrownBy(() -> new DeleteHistory(question))
                .isInstanceOf(ForbiddenException.class);
    }
}
