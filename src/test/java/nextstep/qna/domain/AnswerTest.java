package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(new TextBody(NsUserTest.JAVAJIGI, "Answers Contents1", false), QuestionTest.Q1);
    public static final Answer A2 = new Answer(new TextBody(NsUserTest.SANJIGI, "Answers Contents2", false), QuestionTest.Q1);

    private Answer answer;

    @BeforeEach
    void create() {
        answer = new Answer(new TextBody(NsUserTest.JAVAJIGI, "Answers Contents1", false), QuestionTest.Q1);
    }

    @Test
    @DisplayName("질문자와 답변자가 다른면 에러를 던진다")
    void delete_질문자와_답변자가_다름() {
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI)).isInstanceOf(UnAuthorizedException.class);
    }
}
