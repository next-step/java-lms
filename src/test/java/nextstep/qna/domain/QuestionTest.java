package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionTest {
    private Question Q1;
    private Question Q2;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    public void delete_성공_답변없음() throws Exception {
        //given, when
        List<DeleteHistory> history = Q1.delete();

        //then
        assertTrue(Q1.isDeleted());
        assertThat(history).hasSize(1);
    }

    @Test
    public void delete_실패_질문자와_답변자가_다름() throws Exception {
        //given
        Answer answer = new Answer(Q2.getWriter(), Q1, "answer1");
        Q1.addAnswer(answer);

        //when, then
        assertThatThrownBy(Q1::delete).isInstanceOf(CannotDeleteException.class);
        assertFalse(Q1.isDeleted());
        assertFalse(answer.isDeleted());
    }

    @Test
    public void delete_성공_질문자와_답변자가_같음() throws Exception {
        //given
        Answer answer = new Answer(Q1.getWriter(), Q1, "answer1");
        Q1.addAnswer(answer);

        //when
        List<DeleteHistory> history = Q1.delete();

        //then
        assertTrue(Q1.isDeleted());
        assertTrue(answer.isDeleted());
        assertThat(history).hasSize(2);
    }
}
