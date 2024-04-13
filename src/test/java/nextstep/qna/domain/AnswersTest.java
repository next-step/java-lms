package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    @Test
    void delete_정상() throws CannotDeleteException {
        Answers answers = new Answers(List.of(AnswerTest.A1));
        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    void delete_다른_사람이_쓴_답변이_있을_때() {
        Answers answers = new Answers(List.of(AnswerTest.A1, AnswerTest.A2));

        assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
