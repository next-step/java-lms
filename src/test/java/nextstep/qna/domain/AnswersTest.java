package nextstep.qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {
    @Test
    @DisplayName("Answers 생성자 테스트")
    void initAnswers() {
        Answers answers = new Answers();

        assertThat(answers.getAnswers().size()).isEqualTo(0);
    }

}