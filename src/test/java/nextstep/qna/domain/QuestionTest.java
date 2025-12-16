package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void 질문_삭제에_성공한다() throws Exception {
        Q2.delete(NsUserTest.SANJIGI);

        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void 질문_작성자와_로그인_사용자가_다를_때_삭제를_시도하면_예외가_발생한다() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 질문_작성자가_아닌_다른_사용자가_댓글을_남긴_경우에_삭제를_시도하면_예외가_발생한다() {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 삭제_히스토리를_생성한다() {
        Question question = new Question(NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(NsUserTest.JAVAJIGI, question, "Answers Contents");

        question.addAnswer(answer);

        List<DeleteHistory> deleteHistories = question.deleteHistories();

        assertThat(deleteHistories).hasSize(2);
        assertThat(deleteHistories).contains(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now())
        );
    }
}
