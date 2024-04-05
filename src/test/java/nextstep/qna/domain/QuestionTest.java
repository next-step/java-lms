package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static Question Q1;
    public static Question Q2;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    }

    @Test
    void 댓글없는_질문_삭제() throws CannotDeleteException {
        List<DeleteHistory> delete = Q1.delete();
        int expected = 1;
        assertThat(delete.size()).isEqualTo(expected);
    }

    @Test
    void 모든답변이_질문자와_같을때_삭제() throws CannotDeleteException {
        Q1.addAnswer(new Answer(Q1.getWriter(), Q1, "answer 1"));
        Q1.addAnswer(new Answer(Q1.getWriter(), Q1, "answer 2"));
        Q1.addAnswer(new Answer(Q1.getWriter(), Q1, "answer 3"));
        List<DeleteHistory> delete = Q1.delete();
        int expected = 4;

        assertThat(delete.size()).isEqualTo(expected);
    }

    @Test
    void 질문자와_답변자가_다를때_삭제_하면_예외발생(){
        Q1.addAnswer(new Answer(NsUserTest.SANJIGI, Q1, "answer 1"));
        assertThatThrownBy(Q1::delete)
                .isInstanceOf(CannotDeleteException.class);
    }
}
