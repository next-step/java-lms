package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("QuestionOwner가 아닐 때 Exception 확인 Test")
    @Test
    public void differentQuestionOwnerTest() {
        assertThatThrownBy(() -> Q1.deleteValidation(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @DisplayName("QuestionOwner일 때 확인 Test")
    @Test
    public void sameQuestionOwnerTest() {
        assertThatCode(() -> Q1.deleteValidation(NsUserTest.JAVAJIGI))
            .doesNotThrowAnyException();
    }
}
