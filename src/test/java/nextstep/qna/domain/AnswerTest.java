package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.QuestionTest.Q1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    void delete_다른_사람이_쓴_답변이_있어_삭제할수_없습니다_예외() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    void delete_상태값_변경_확인() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertTrue(A1.isDeleted());
    }

    @Test
    void delete_반환값_확인() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = A1.delete(NsUserTest.JAVAJIGI);
        assertThat(deleteHistories.size()).isEqualTo(1);
    }

}
