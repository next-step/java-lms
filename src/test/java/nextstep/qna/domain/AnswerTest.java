package nextstep.qna.domain;

import static nextstep.users.domain.NsUserTest.*;
import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("대답 등록자와 삭제 요청자가 다르면 CannotDeleteException을 발생시킨다.")
    @Test
    void valid_question() {
        assertThatThrownBy(() -> A1.delete(SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("delete 메서드 호출시 사용자가 soft delete된다.")
    @Test
    void delete() throws CannotDeleteException {
        A1.delete(JAVAJIGI);
        assertThat(A1.isDeleted()).isEqualTo(true);
    }
}
