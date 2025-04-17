package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    private Question question;

    @BeforeEach
    void setUp() {
        question = new Question(JAVAJIGI, "title1", "contents1");
    }

    @Test
    @DisplayName("정상적으로 질문이 삭제되는 케이스")
    void delete_normal_case() {
        List<DeleteHistory> histories = question.delete(JAVAJIGI);

        assertTrue(question.isDeleted());
        assertThat(histories).hasSize(1);
    }

    @Test
    @DisplayName("권한 없는 사용자가 질문 삭제 요청하면 예외를 던진다")
    void cannot_delete_question_another_user() {
        assertThatThrownBy(() -> question.delete(SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("다른 사람이 쓴 답변이 존재하면 삭제 요청시 예외를 던진다")
    void cannot_delete_answer_another_user() {
        Answer answer = new Answer(2L, SANJIGI, question, "answer");
        question.addAnswer(answer);

        assertThatThrownBy(() -> question.delete(JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
