package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("성공적으로 삭제한다")
    void delete() {
        assertThatNoException()
            .isThrownBy(() -> Q1.delete(NsUserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("글 작성자가 아닌데 삭제하려하면 예외가 발생한다")
    void delete_fail_for_not_owner() {
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
