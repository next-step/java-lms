package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
        "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
        "Answers Contents2");

    @DisplayName("질문에 다른 유저가 작성한 답글이 있으면 예외를 던진다")
    @Test
    void test1() throws Exception {
        assertThatThrownBy(() -> {
            A1.isOwner(NsUserTest.SANJIGI);
        })
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @DisplayName("답변이 삭제될 시 답변의 상태가 삭제로 변경되고 삭제 히스토리에 추가된다.")
    @Test
    void test2() throws Exception {
        LocalDateTime now = LocalDateTime.now();
        DeleteHistory result = A1.delete(now);

        assertThat(result).isEqualTo(
            new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, now)
        );
    }
}
