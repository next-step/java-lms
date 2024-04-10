package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("질문의 댓글 작성자가 아닐 때 삭제 할 수 없음")
    void question_not_ower() {
        assertThatThrownBy(() -> A2.checkOwner(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
