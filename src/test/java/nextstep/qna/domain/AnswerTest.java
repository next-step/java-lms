package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 답변삭제불가검증() {
        assertThatThrownBy(() -> {
            A1.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class).hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void 답변삭제테스트() throws CannotDeleteException {
        DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI);

        Assertions.assertThat(A1.isDeleted()).isTrue();
        Assertions.assertThat(deleteHistory).isEqualTo(
                new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now()));
    }
}
