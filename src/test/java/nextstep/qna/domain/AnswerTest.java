package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.SetUp.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    @DisplayName("답변삭제")
    @Test
    void answerDelete() {
        Answer answer = createAnswer1(NsUserTest.JAVAJIGI);

        LocalDateTime now = LocalDateTime.now();
        DeleteHistory deleteHistory = answer.delete(NsUserTest.JAVAJIGI, now);

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), now));
        assertThat(answer.isDeleted()).isEqualTo(true);
    }

    @DisplayName("답변 입력한 사람 다르면 에러")
    @Test
    void answerWriterDiffException() {
        Answer answer = createAnswer1(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class);
    }
}
