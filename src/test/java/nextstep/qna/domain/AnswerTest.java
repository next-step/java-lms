package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.Fixtures.createAnswer;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    @Test
    @DisplayName("answer 를 삭제한다.")
    void test01() {
        Answer answer = createAnswer(NsUserTest.JAVAJIGI);

        LocalDateTime now = LocalDateTime.now();
        DeleteHistory deleteHistory = answer.delete(NsUserTest.JAVAJIGI, now);

        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), now));
        assertThat(answer.isDeleted()).isEqualTo(true);
    }

    @Test
    @DisplayName("답변을 입력한 사람이 다르면 에러 발생")
    void test02() {
        Answer answer = createAnswer(NsUserTest.JAVAJIGI);

        assertThatThrownBy(() -> answer.delete(NsUserTest.SANJIGI, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class);
    }
}
