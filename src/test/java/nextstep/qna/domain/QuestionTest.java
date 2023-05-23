package nextstep.qna.domain;

import static nextstep.qna.domain.AnswerTest.A2;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void shouldThrowCannotDeleteException_ifOwnerIsNotLoginUser() {
        //given
        NsUser loginUser = NsUserTest.SANJIGI;
        //when & then
        assertThatThrownBy(() -> {
            Q1.ensureOwnedByUser(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void shouldThrowCannotDeleteException_ifAnswerIsNotByLoginUser() {
        //given
        NsUser loginUser = NsUserTest.JAVAJIGI;
        Q1.addAnswer(A2);
        //when & then
        assertThatThrownBy(() -> {
            Q1.ensureAllAnswersOwnedByUser(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
