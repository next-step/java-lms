package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    public static final LocalDateTime NOW = LocalDateTime.now();

    @Test
    void delete_다른_사람이_쓴_답변() throws Exception {
        assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI, NOW))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공() throws Exception {
        assertThat(A1.isDeleted()).isFalse();
        assertThat(A1.delete(NsUserTest.JAVAJIGI, NOW)).isEqualTo(new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), NOW));
        assertThat(A1.isDeleted()).isTrue();
    }
}
