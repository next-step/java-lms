package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.Fixtures.AnswerFixtures.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    @DisplayName("답변삭제")
    @Test
    void answerDelete() {
        Answer answer = createAnswer1(NsUserTest.JAVAJIGI);

        DeleteHistory deleteHistory = answer.delete(NsUserTest.JAVAJIGI);

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
        assertThat(answer.isDeleted()).isEqualTo(true);
    }

    @DisplayName("답변 입력한 사람 다르면 에러")
    @Test
    void answerWriterDiffException() {
        Answer answer = createAnswer1(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
