package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 작성자와_다른_유저는_삭제할_수_없다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("작성자가 아닌경우 삭제할 수 없습니다.");
    }

    @Test
    void 답변이_삭제되면_DeleteHistory를_반환한다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(),
                NsUserTest.JAVAJIGI, LocalDateTime.now());

        DeleteHistory deleteHistory = answer.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
    }
}
