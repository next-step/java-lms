package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question QUESTION = new Question(JAVAJIGI, "title1", "contents1");

    @Nested
    @DisplayName("delete() 테스트")
    class DeleteTest {
        @Test
        @DisplayName("user와 Question.writer가 일치하지 않는 경우 CannotDeleteException이 발생한다.")
        void testFailCase() {
            NsUser user = SANJIGI;
            Question question = new Question(JAVAJIGI, "title1", "contents1");

            assertThatThrownBy(() -> question.deleteByUser(user)).isExactlyInstanceOf(CannotDeleteException.class);
        }

        @Test
        @DisplayName("삭제에 성공한 경우 Question.deleted가 true로 변하며 List<DeleteHistory>를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = JAVAJIGI;
            Question question = new Question(JAVAJIGI, "title1", "contents1");
            DeleteHistory expectedDeleteHistory = DeleteHistory.questionOf(question.getId(), question.getWriter());

            List<DeleteHistory> deleteHistories = question.deleteByUser(user);

            assertThat(question.isDeleted()).isTrue();
            assertThat(deleteHistories.get(0)).isEqualTo(expectedDeleteHistory);
        }
    }
}
