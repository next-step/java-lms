package nextstep.qna.domain;

import java.util.List;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class QuestionTest {

    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_성공() throws Exception {
        // givne
        Q2.addAnswer(AnswerTest.A2);

        // when
        List<DeleteHistory> deleteHistories = Q2.deleteBy(NsUserTest.SANJIGI);

        // then
        assertAll(
            () -> assertThat(Q2.isDeleted()).isTrue(),
            () -> assertThat(AnswerTest.A2.isDeleted()).isTrue(),
            () -> assertThat(deleteHistories).hasSize(2)
        );
    }

    @Test
    public void delete_다른_사람이_쓴_글은_삭제할_수_없다() throws Exception {
        assertThatThrownBy(() -> Q1.deleteBy(NsUserTest.SANJIGI)).isInstanceOf(
            CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        // given
        Q2.addAnswer(AnswerTest.A2);

        // when
        Q2.deleteBy(NsUserTest.SANJIGI);

        // then
        assertThat(Q2.isDeleted()).isTrue();
        assertThat(AnswerTest.A2.isDeleted()).isTrue();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글은_삭제할_수_없다() throws Exception {
        Q1.addAnswer(AnswerTest.A2);
        assertThatThrownBy(() -> Q1.deleteBy(NsUserTest.JAVAJIGI)).isInstanceOf(
            CannotDeleteException.class);
    }
}
