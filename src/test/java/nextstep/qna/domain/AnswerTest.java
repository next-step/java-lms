package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setup() {
        answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    }

    @Test
    void delete() throws Exception {
        DeleteHistory actual = answer.delete(NsUserTest.JAVAJIGI);
        DeleteHistory expected = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());
        assertThat(actual).isEqualTo(expected);
    }

    @Test
    void 삭제시_작성자가_아니면_CannotDeleteException() throws Exception {
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
