package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @DisplayName("현재 로그인 계정과 질문 작성자가 다르면 예외가 발생한다.")
    @Test
    void test01() {
        assertThatThrownBy(() -> Q1.deleteBy(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("현재 로그인 계정과 질문자가 다릅니다.");
    }

    @DisplayName("현재 로그인 계정과 질문 작성자가 같다면 질문을 삭제한다.")
    @Test
    void test02() throws CannotDeleteException {
        Q1.deleteBy(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
