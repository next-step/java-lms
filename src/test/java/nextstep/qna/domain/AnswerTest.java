package nextstep.qna.domain;

import java.util.Arrays;
import java.util.List;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 일급 컬렉션 테스트")
    public void answers_create_test() {
        // given
        Answers answers = Answers.defaultOf();
        // when
        answers.add(A1);
        // then
        Assertions.assertThat(answers.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("답변과 함께 초기화")
    public void add_answer_with_construct() {
        // given
        List<Answer> list = Arrays.asList(A1, A2);

        // when
        Answers answers = Answers.defaultOf(list);

        // then
        Assertions.assertThat(answers.size()).isEqualTo(2);
    }
}
