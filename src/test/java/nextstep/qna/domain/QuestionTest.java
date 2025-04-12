package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 작성자와 삭제 요청자가 다른 경우 예외처리")
    void delete_by_not_writer() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        assertThatThrownBy(() -> question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("질문 답변 중 질문 작성자가 아닌 다른 사람 답변이 있는 경우 예외처리")
    void delete_with_not_writer_answer() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Test1"));
        question.addAnswer(new Answer(NsUserTest.SANJIGI, question, "Answers Test1"));
        assertThatThrownBy(() -> question.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("질문 작성자와 삭제 요청자가 같고, 질문에 달린 모든 답변이 질문 작성자의 답변인 경우 정상 삭제")
    void delete_success() throws CannotDeleteException {
        Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Test2"));
        question.addAnswer(new Answer(NsUserTest.JAVAJIGI, question, "Answers Test2"));

        assertThat(question.isDeleted()).isFalse();
        List<DeleteHistory> histories = question.delete(NsUserTest.JAVAJIGI);
        assertThat(histories.size()).isEqualTo(3);
        assertThat(question.isDeleted()).isTrue();
    }
}
