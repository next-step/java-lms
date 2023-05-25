package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import java.time.LocalDateTime;
import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    void 삭제_삭제이력() throws CannotDeleteException {
        Answer answer = new Answer(1L, NsUser.GUEST_USER, QuestionTest.Q1, "question1");
        LocalDateTime deleteTime = LocalDateTime.now();
        DeleteHistory actual = answer.delete(NsUser.GUEST_USER, deleteTime);

        assertThat(actual).isEqualTo(new DeleteHistory(ContentType.ANSWER, 1L, NsUser.GUEST_USER, deleteTime));
    }

    @Test
    void 삭제_상태변경() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI, LocalDateTime.now());
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    void 삭제불가_다른작성자() {
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> A1.delete(NsUserTest.SANJIGI, LocalDateTime.now()))
            .withMessageMatching("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
