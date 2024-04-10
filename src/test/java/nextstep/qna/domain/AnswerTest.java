package nextstep.qna.domain;

import static nextstep.qna.ExceptionMessage.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    @Nested
    @DisplayName("deleteByUser() 테스트")
    class DeleteByUserTest {
        Answer answer;

        @Test
        @DisplayName("user와 Answer.writer가 일치하지 않는 경우 CannotDeleteException이 발생한다.")
        void testFailCase() {
            NsUser user = NsUserTest.SANJIGI;
            Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents");
            assertThatThrownBy(() -> answer.deleteByUser(user))
                .isExactlyInstanceOf(CannotDeleteException.class)
                .hasMessage(NO_AUTHORITY_TO_DELETE_ANSWER.message());
        }
        @Test
        @DisplayName("삭제가 진행되는 경우 Answer.deleted가 true로 변하며 DeleteHistory를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = NsUserTest.JAVAJIGI;
            Answer answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents");
            DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());

            DeleteHistory deleteHistory = answer.deleteByUser(user);

            assertThat(answer.isDeleted()).isTrue();
            assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
        }
    }
}