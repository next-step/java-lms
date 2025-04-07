package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @Test
    void shouldNotAllowDelete_WhenUserIsNotOwner(){
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(()->Q1.delete(NsUserTest.SANJIGI))
                .withMessage("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    void shouldAllowDelete_WhenUserIsOwner() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }
}
