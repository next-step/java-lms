package nextstep.qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    private Answer answer;

    @BeforeEach
    void setUp() {
        answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents");
    }

    @Test
    public void 생성() {
        assertThat(answer).isInstanceOf(Answer.class);
    }

    @Test
    public void 삭제_성공() throws CannotDeleteException {
        answer.delete();

        assertThat(answer.isDeleted()).isTrue();
        assertThat(answer.deleteHistory()).isPresent();
    }

    @Test
    public void 삭제_실패_질문자와_답변자가_다른_경우() {
        Answer answer = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents");

        assertThatThrownBy(() -> answer.delete())
            .isInstanceOf(CannotDeleteException.class);
    }
}
