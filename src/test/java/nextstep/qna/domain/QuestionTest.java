package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("질문을 삭제하고 삭제 히스토리를 반환 받을 수 있다.")
    @Test
    void delete() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.GREEN, "title", "content");
        DeleteHistories result = question.delete(NsUserTest.GREEN);
        assertThat(result).isEqualTo(
                new DeleteHistories(List.of(new DeleteHistory(ContentType.QUESTION, 1L, NsUserTest.GREEN, LocalDateTime.now())))
        );
    }

    @DisplayName("질문과 연관된 답변을 삭제하고 삭제 히스토리를 반환 받을 수 있다.")
    @Test
    void deleteWithAnswers() throws CannotDeleteException {
        Question question = new Question(1L, NsUserTest.GREEN, "title", "content");
        Answer greensAnswer1 = new Answer(NsUserTest.GREEN, question, "답변1");
        Answer greensAnswer2 = new Answer(NsUserTest.GREEN, question, "답변2");
        question.addAnswers(List.of(
                greensAnswer1,
                greensAnswer2
        ));

        DeleteHistories result = question.delete(NsUserTest.GREEN);

        assertThat(result).isEqualTo(
                new DeleteHistories(List.of(
                        DeleteHistory.questionOf(1L, NsUserTest.GREEN, LocalDateTime.now()),
                        DeleteHistory.answerOf(greensAnswer1, LocalDateTime.now()),
                        DeleteHistory.answerOf(greensAnswer2, LocalDateTime.now())
                )));
    }
    @DisplayName("질문에 다른 사람의 답변이 있을 경우 예외가 발생한다.")
    @Test
    void deleteThrowException() {
        Question question = new Question(1L, NsUserTest.GREEN, "title", "content");
        Answer greensAnswer1 = new Answer(NsUserTest.GREEN, question, "답변1");
        Answer greensAnswer2 = new Answer(NsUserTest.JAVAJIGI, question, "답변2");
        question.addAnswers(List.of(
                greensAnswer1,
                greensAnswer2
        ));

        assertThatThrownBy(() -> question.delete(NsUserTest.GREEN))
                .isInstanceOf(CannotDeleteException.class);
    }
}
