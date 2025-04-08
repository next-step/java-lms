package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 시 질문자와 답변자 불일치할 경우 예외 발생")
    @Test
    void delete_질문자와_답변자_불일치() {
        Answer answer = new Answer(1L, NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents1");
        Assertions.assertThatThrownBy(answer::delete).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 삭제")
    @Test
    void delete() throws CannotDeleteException {
        Answer answer = new Answer(1L, NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, 1L, NsUserTest.JAVAJIGI, LocalDateTime.now());

        DeleteHistory actual = answer.delete();

        Assertions.assertThat(actual).isEqualTo(expected);
    }
}
