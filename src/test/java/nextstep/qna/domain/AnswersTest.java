package nextstep.qna.domain;

import nextstep.qna.domain.answer.Answers;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @DisplayName("isAllSameAnsererWith는 비교 대상 사용자와 동일한 답변 작성자로 구성되어 있는지 판단한다.")
    @Test
    void isAllSameAnswererWith() {
        final Answers answers = new Answers();
        answers.add(AnswerTest.A1, QuestionTest.Q1);
        answers.add(AnswerTest.A1, QuestionTest.Q1);

        assertThat(answers.isAllSameAnswererWith(NsUserTest.JAVAJIGI))
                .isEqualTo(true);
    }

}
