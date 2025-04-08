package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void delete() throws CannotDeleteException {
        final LocalDateTime createdDate = LocalDateTime.now();
        assertThat(A1.delete(NsUserTest.JAVAJIGI, createdDate)).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, createdDate));
    }

    @Test
    void 다른_사람이_쓴_답변이_있어_삭제할_수_없습니다() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> A1.delete(NsUserTest.SANJIGI, LocalDateTime.now()));
    }
}
