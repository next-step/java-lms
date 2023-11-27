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
    void valid_question() throws CannotDeleteException {
        assertThatThrownBy(() -> A1.validAnswer(SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
