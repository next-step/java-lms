package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 작성자와_다른_유저는_삭제할_수_없다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void 답이_삭제되면_DeleteHistory를_반환한다() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(),
                NsUserTest.JAVAJIGI, LocalDateTime.now());

        DeleteHistory deleteHistory = answer.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
    }
}
