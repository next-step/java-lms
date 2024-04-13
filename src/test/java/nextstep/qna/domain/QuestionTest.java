package nextstep.qna.domain;

import static nextstep.qna.ExceptionMessage.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("로그인 사용자와 질문한 사람이 같은 경우 삭제 가능하다.")
    void testFailCase() {
        NsUser user = NsUserTest.SANJIGI;
        assertThatThrownBy(() -> Q1.delete(user))
            .isExactlyInstanceOf(CannotDeleteException.class)
            .hasMessage(NO_AUTHORITY_TO_DELETE_QUESTION.message());
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.")
    void test2() {
        String expectingMessage = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
        Answer answer = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents");
        Q1.addAnswer(answer);

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.verifyIfErasable(NsUserTest.JAVAJIGI))
            .withMessageMatching(expectingMessage);
    }
}
