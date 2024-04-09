package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question QUESTION = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

    @Nested
    @DisplayName("delete() 테스트")
    class DeleteTest {
        @Test
        @DisplayName("삭제에 실패한 경우 CannotDeleteException이 발생한다.")
        void testFailCase() {
            NsUser user = NsUserTest.SANJIGI;
            Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");

            assertThatThrownBy(() -> question.deleteByUser(user)).isExactlyInstanceOf(CannotDeleteException.class);
        }

        @Test
        @DisplayName("삭제에 성공한 경우 Question.deleted가 true로 변하며 List<DeleteHistory>를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = NsUserTest.JAVAJIGI;
            Question question = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
            DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now());

            List<DeleteHistory> deleteHistories = question.deleteByUser(user);

            assertThat(question.isDeleted()).isTrue();
            assertThat(deleteHistories.get(0)).isEqualTo(expectedDeleteHistory);
        }
    }
}
