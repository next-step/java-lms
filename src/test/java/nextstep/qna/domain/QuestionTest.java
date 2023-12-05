package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");


    @Test
    @DisplayName("Question 삭제")
    void delete() throws CannotDeleteException {
        Question question = Q1;
        question.delete(NsUserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("Question 삭제시 타인이면 예외발생")
    void delete_exception_other_user_try(){
        Question question = Q1;
        assertThatThrownBy(()->question.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }
}
