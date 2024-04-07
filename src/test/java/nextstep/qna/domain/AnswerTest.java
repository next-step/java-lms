package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("delete는")
    @Nested
    class Describe_delete {

        @DisplayName("답변 작성자와 지우려고 하는 작성자가 같을 때 삭제할 수 있다.")
        @Test
        void can_delete_when_same_writer() {
            assertThatCode(() -> A1.delete(NsUserTest.JAVAJIGI, LocalDateTime.now()))
                    .doesNotThrowAnyException();
        }

        @DisplayName("답변 작성자와 지우려고 하는 작성자가 다를 때, CannnotDeleteException을 발생시킨다.")
        @Test
        void cannot_delete_when_other_writer() {
            assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI, LocalDateTime.now()))
                    .isInstanceOf(CannotDeleteException.class)
                    .hasMessage("답변을 삭제할 권한이 없습니다.");
        }
    }
}
