package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        Answer answer1 = AnswerTest.ofUser(11L, NsUserTest.JAVAJIGI);
        Answers answers = new Answers(Arrays.asList(answer1));

        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(answer1.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        Answer answer1 = AnswerTest.ofUser(11L, NsUserTest.JAVAJIGI);
        Answer answer2 = AnswerTest.ofUser(12L, NsUserTest.SANJIGI);
        Answers answers = new Answers(Arrays.asList(answer1, answer2));

        assertThatThrownBy(() -> {
            answers.delete(NsUserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
