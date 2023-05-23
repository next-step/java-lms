package nextstep.qna.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

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
    void delete() {
        //given
        Answer answer = new Answer();
        Answers answers = new Answers(Arrays.asList(answer, answer));

        //when
        List<DeleteHistory> result = answers.delete();

        //then
        assertThat(result.size()).isEqualTo(2);
    }
}
