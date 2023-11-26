package nextstep.qna.domain;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    private static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    private static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");
    private static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    private static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    @Test
    public void delete_질문글작성자_답변글작성자_로그인유저_동일_시_성공_삭제_히스토리_확인() throws CannotDeleteException {
        Q1.addAnswer(A1);

        List<DeleteHistory> expected = Arrays.asList(
                new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));

        assertThat(Q1.delete(NsUserTest.JAVAJIGI)).isEqualTo(expected);
    }

    @Test
    public void delete_질문글작성자_답변글작성자_동일하나_로그인유저_다를_시_에러_확인() {
        Q1.addAnswer(A1);

        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_질문글작성자_로그인유저_동일하나_답변글작성자_다를_시_에러_확인() {
        Q1.addAnswer(A2);

        assertThatThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_답변글작성자_로그인유저_동일하나_질문글작성자_다를_시_에러_확인() {
        Q2.addAnswer(A1);

        assertThatThrownBy(() -> Q2.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
