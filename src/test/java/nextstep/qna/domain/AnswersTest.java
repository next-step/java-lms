package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {

    private Answers answers;

    @Test
    public void delete_성공() throws Exception {
        answers = new Answers(Arrays.asList(AnswerTest.A3, AnswerTest.A4));

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        answers.delete(NsUserTest.SANJIGI, deleteHistories);

        assertThat(AnswerTest.A3.isOwner(NsUserTest.SANJIGI)).isTrue();
        assertThat(AnswerTest.A4.isOwner(NsUserTest.SANJIGI)).isTrue();

        assertThat(deleteHistories.size()).isEqualTo(2);
    }

    @Test
    public void delete_다른_사람이_쓴_답변() throws Exception {
        answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThatThrownBy(() -> {
            answers.delete(NsUserTest.JAVAJIGI, new ArrayList<>());
        }).isInstanceOf(CannotDeleteException.class);
    }
}
