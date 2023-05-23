package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void delete_성공() throws CannotDeleteException {
        Q1.addAnswer(AnswerTest.A1);
        assertThat(Q1.isDeleted()).isFalse();
        Q1.delete(NsUserTest.JAVAJIGI, Q1.getId());
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(Q1.getAnswers().get(0).isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, Q1.getId()))
                .isInstanceOf(CannotDeleteException.class);
    }
}
