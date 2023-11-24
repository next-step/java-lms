package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제시 다른사람이 작성한 댓글이라면 삭제가 불가능하다.")
    void deleteAnswer_other_writer() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "test");

        assertThatThrownBy(() -> answer.deleteAnswer(NsUserTest.SANJIGI, LocalDateTime.now()))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessageContaining("답변은 작성자만 삭제가 가능합니다.");
    }

    @Test
    @DisplayName("본인이 작성한 답변이라면 삭제가 가능하다.")
    void deleteAnswer_writer() {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "test");
        LocalDateTime deleteTime = LocalDateTime.now();

        assertThat(answer.deleteAnswer(NsUserTest.JAVAJIGI, deleteTime))
                .isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, deleteTime));
        assertThat(answer.isDeleted()).isTrue();
    }
}
