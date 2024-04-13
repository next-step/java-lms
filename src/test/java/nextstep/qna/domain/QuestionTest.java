package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 작성자의 질문만 삭제할 수 있다.")
    void delete_success() throws CannotDeleteException {

        Question question = Q1;
        question.deleteWithAnswers(NsUserTest.JAVAJIGI);
        Assertions.assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문 작성자가 아니라면 질문을 삭제할 수 없다.")
    void delete_fail() {

        Question question = Q1;

        Assertions.assertThatThrownBy(() ->
                question.deleteWithAnswers(NsUserTest.SANJIGI)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
