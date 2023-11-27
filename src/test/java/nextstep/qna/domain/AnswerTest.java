package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 데이터를 soft delete 한다.")
    void 답변_데이터를_SOFT_DELETE_할_수_있다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, new Question(NsUserTest.JAVAJIGI, "title1", "contents1"), "Answers Contents1");
        boolean result = answer.delete();
        assertThat(result).isTrue();
    }
}
