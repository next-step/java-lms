package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("질문자와 답변자가 다른면 에러를 던진다")
    void delete_다른_사람이_쓴_글() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI, LocalDateTime.now())).isInstanceOf(UnAuthorizedException.class);
    }

    @Test
    @DisplayName("질문자와 답변자가 같은 경우 답변 상태를 변경하고 삭제 히스토리를 리턴")
    void delete_성공() {
        Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        DeleteHistory deleteHistory = answer.delete(NsUserTest.JAVAJIGI, LocalDateTime.now());

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()));
        assertThat(answer.isDeleted()).isTrue();

    }
}
