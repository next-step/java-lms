package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws Exception {

    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> Q1.deleteBy(NsUserTest.SANJIGI)).isInstanceOf(
            CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        // given
        Q1.addAnswer(AnswerTest.A1);

        // when
        Q1.deleteBy(NsUserTest.JAVAJIGI);

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.deleteBy(NsUserTest.JAVAJIGI)).isInstanceOf(
            CannotDeleteException.class);
    }
}
