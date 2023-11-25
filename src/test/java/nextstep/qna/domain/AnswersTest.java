package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("답변 삭제시 다른 사람이 작성한 답변글이 있으면 삭제에 실패한다.")
    void deleteAnswers_other_writer() {
        Answers answers = new Answers();
        answers.add(AnswerTest.A2);

        assertThatThrownBy(() -> answers.deleteAnswers(NsUserTest.JAVAJIGI, LocalDateTime.now()))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessageContaining("답변은 작성자만 삭제가 가능합니다.");
    }

    @Test
    @DisplayName("답변 삭제시 삭제 권한이 있으면 정상적으로 삭제된다.")
    void deleteAnswers() {
        Answers answers = new Answers();
        answers.add(new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "test"));
        LocalDateTime deleteTime = LocalDateTime.now();

        assertThat(answers.deleteAnswers(NsUserTest.JAVAJIGI, deleteTime)).hasSize(1)
                .contains(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, deleteTime));
    }
}
