package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    public void shouldThrowException_ifOwnerIsNotLoginUser() {
        //given
        NsUser wrongUser = NsUserTest.SANJIGI;
        //when & then
        assertThatThrownBy(() -> {
            Q1.hasAuthorityToDelete(wrongUser);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
