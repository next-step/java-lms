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

class AnswersTest {
    @Nested
    @DisplayName("deleteByUser() 테스트")
    class DeleteByUserTest {
        @Test
        @DisplayName("삭제를 실패한 경우 CannotDeleteException라는 예외가 발생한다.")
        void testFailCase() {
            NsUser user = NsUserTest.SANJIGI;
            Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.QUESTION, "Answers Contents");
            Answers answers = new Answers(List.of(answer));

            assertThatThrownBy(() -> answers.deleteByUser(user)).isExactlyInstanceOf(CannotDeleteException.class);
        }

        @Test
        @DisplayName("삭제를 성공한 경우 List<DeleteHistory>를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = NsUserTest.JAVAJIGI;
            Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.QUESTION, "Answers Contents");
            Answers answers = new Answers(List.of(answer));
            DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());

            List<DeleteHistory> deleteHistories = answers.deleteByUser(user);

            assertThat(deleteHistories.get(0)).isEqualTo(expectedDeleteHistory);
        }
    }
}
