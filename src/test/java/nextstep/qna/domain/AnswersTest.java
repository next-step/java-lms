package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class AnswersTest {

    public static final Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

    @DisplayName("답변을 삭제하면 삭제 기록에 담긴다")
    @Test
    void deleteAnswer() {
        Answers answers = new Answers();
        answers.add(answer);

        assertThat(answers.deleteAnswer(NsUserTest.JAVAJIGI)).hasSize(1);
        assertThat(answers.deleteAnswer(NsUserTest.JAVAJIGI)).containsExactly(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @DisplayName("답변을 삭제할 떄 로그인된 유저와 답변의 작성자가 아닐 경우 예외를 던진다")
    @Test
    void deleteAnswerException() {
        Answers answers = new Answers();
        answers.add(answer);
        assertThatThrownBy(() -> answers.deleteAnswer(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}