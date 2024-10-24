package nextstep.qna.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @Test
    void deleteAll() {
        Answers answers = new Answers(List.of(new Answer(), new Answer()));
        assertThat(answers.deleteAll()).hasSize(2);
    }
}
