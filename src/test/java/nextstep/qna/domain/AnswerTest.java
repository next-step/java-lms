package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변을 삭제한다.")
    @Test
    void 답변을_삭제한다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory history = answer.deleteAnswer(NsUserTest.JAVAJIGI);
        assertThat(history).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter()));
    }

    @DisplayName("답변 작성자가 아니라서 삭제할 수 없다.")
    @Test
    void 답변_작성자가_아니라서_삭제할_수_없다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThatThrownBy(() -> {
            answer.deleteAnswer(NsUserTest.SANJIGI);
        }).isInstanceOf(UnAuthorizedException.class);
    }
}