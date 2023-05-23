package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class AnswersTest {

    @Test
    void create() {
        //given
        Answer answer = new Answer();

        //when
        Answers answers = new Answers(Arrays.asList(answer, answer));

        //then
        assertThat(answers.value().size()).isEqualTo(2);
    }

    @Test
    void delete() throws CannotDeleteException {
        //given
        Answers answers = new Answers(Arrays.asList(A1, A1));

        //when
        List<DeleteHistory> result = answers.delete(JAVAJIGI);

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
