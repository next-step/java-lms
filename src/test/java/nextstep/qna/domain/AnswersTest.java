package nextstep.qna.domain;

import nextstep.qna.exception.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    private List<Answer> answerList;
    private Answers answers;

    @BeforeEach
    void setup() {
        answerList = List.of(A1, A2);
        answers = new Answers(answerList);
    }

    @DisplayName("Answer 도메인의 일급컬렉션을 생성할 수 있다.")
    @Test
    void createAnswersTest() {
        assertThat(answers.size()).isEqualTo(2);
    }

    @DisplayName("답변글들에 다른 사람이 쓴 답변이 있으면 예외가 발생한다.")
    @Test
    void deleteExceptionTest() {
        Assertions.assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
