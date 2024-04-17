package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    public static final Answers ANSWERS = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

    @Test
    @DisplayName("Answers 생성자 테스트")
    public void initAnswers() {
        Answers answers = new Answers();

        assertThat(answers.getAnswers().size()).isEqualTo(0);
    }

    @Test
    @DisplayName("Answers 추가 테스트")
    public void addAnswers() {
        Answers answers = new Answers();
        answers.addAnswer(AnswerTest.A1);
        answers.addAnswer(AnswerTest.A2);

        assertThat(ANSWERS.getAnswers().size()).isEqualTo(2);
    }

    @Test
    @DisplayName("Answers 작성자 검증 테스트")
    public void validateWriterTest() {
        assertThatThrownBy(() -> ANSWERS.validateDelete(NsUserTest.NEWUSER))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}