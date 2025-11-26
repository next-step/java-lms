package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void 답변없는질문_삭제() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
        assertThat(deleteHistories.get(0).getContentType()).isEqualTo(ContentType.QUESTION);
    }

    @Test
    public void 질문자와_답변자가_같은경우_삭제() throws CannotDeleteException {
        Answer answer1 = new Answer(11L, NsUserTest.JAVAJIGI, Q1, "answer1");
        Answer answer2 = new Answer(12L, NsUserTest.JAVAJIGI, Q1, "answer2");
        Q1.addAnswer(answer1);
        Q1.addAnswer(answer2);

        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
        assertThat(answer2.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(3);
    }

    @Test
    public void 다른사람의_답변이_있는경우_삭제() {
        Question question = new Question(1L, NsUserTest.JAVAJIGI, "title", "contents");
        Answer answer = new Answer(11L, NsUserTest.SANJIGI, question, "answer");
        question.addAnswer(answer);

        assertThatThrownBy(() -> {
            question.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("다른 사람의 답변이 존재하여 삭제할 수 없습니다.");
    }

    @Test
    public void 질문자가_아닌경우_삭제불가() {
        assertThatThrownBy(() -> {
            Q1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
