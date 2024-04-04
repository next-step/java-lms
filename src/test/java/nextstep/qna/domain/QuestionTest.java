package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    private Question question;

    @Test
    public void delete_성공() throws Exception {
        Q2.addAnswer(AnswerTest.A3);
        Q2.addAnswer(AnswerTest.A4);

        assertThat(Q2.isDeleted()).isFalse();
        assertThat(AnswerTest.A3.isDeleted()).isFalse();
        assertThat(AnswerTest.A4.isDeleted()).isFalse();

        List<DeleteHistory> deleteHistories = Q2.delete(NsUserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(AnswerTest.A3.isDeleted()).isTrue();
        assertThat(AnswerTest.A4.isDeleted()).isTrue();

        assertEquals(3, deleteHistories.size());
    }

    @Test
    public void delete_다른_사람이_쓴_질문() throws Exception {
        assertThat(Q2.isDeleted()).isFalse();
        assertThatThrownBy(() -> {
            Q2.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
