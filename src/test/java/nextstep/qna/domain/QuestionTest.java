package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("로그인한 사용자의 질문 테스트")
    public void testIsLoginUserQuestion(){
        boolean isLoginUser = Q1.isOwner(NsUserTest.JAVAJIGI);
        assertThat(isLoginUser).isTrue();
    }

    @Test
    @DisplayName("로그인한 사용자가 아닌 질문 테스트")
    public void testIsNotLoginUserQuestion(){
        boolean isLoginUser = Q1.isOwner(NsUserTest.SANJIGI);
        assertThat(isLoginUser).isFalse();
    }

    @Test
    @DisplayName("미삭제 상태 질문 테스트")
    public void testIsNotDeleted(){
        boolean isDeleted = Q1.isDeleted();
        assertThat(isDeleted).isFalse();
    }

    @Test
    @DisplayName("삭제 상태 질문 테스트")
    public void testIsDeleted(){
        Q1.setDeleted(true);
        boolean isDeleted = Q1.isDeleted();
        assertThat(isDeleted).isTrue();
    }
}
