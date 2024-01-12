package nextstep.qna.domain;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

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
    void test1() throws CannotDeleteException {
        String expectingMessage = "질문을 삭제할 권한이 없습니다.";

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.verifyIfErasable(NsUserTest.SANJIGI))
            .withMessageMatching(expectingMessage);
    }

    @Test
    @DisplayName("질문자와 답변글의 모든 답변자가 같은 경우 삭제가 가능하다.")
    void test2() throws CannotDeleteException {
        String expectingMessage = "다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.";
        Q1.addAnswer(AnswerTest.A2);

        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.verifyIfErasable(NsUserTest.JAVAJIGI))
            .withMessageMatching(expectingMessage);
    }
}
