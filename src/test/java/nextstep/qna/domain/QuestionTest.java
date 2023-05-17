package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.User;
import nextstep.users.domain.UserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class QuestionTest {
    public static final Question QUESTION_BY_JAVAJIGI = new Question(UserTest.JAVAJIGI, "title1", "contents1");
    public static final Question QUESTION_BY_SANJIGI = new Question(UserTest.SANJIGI, "title2", "contents2");

    @DisplayName("답변이 없는 경우 질문 삭제가 가능하다.")
    @Test
    void question_can_be_deleted_if_no_answers_exist() throws CannotDeleteException {
        User writer = UserTest.JAVAJIGI;
        Question question = QUESTION_BY_JAVAJIGI;

        question.delete(writer);

        Assertions.assertThat(question.isDeleted()).isTrue();
    }
}
