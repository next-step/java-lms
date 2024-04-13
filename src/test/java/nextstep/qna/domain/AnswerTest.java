package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static nextstep.qna.ExceptionMessage.NO_AUTHORITY_TO_DELETE_ANSWER;
import static nextstep.qna.domain.QuestionTest.QUESTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer ANSWER_OF_JAVAJIGI = new Answer(JAVAJIGI, QUESTION, "Answers Contents");
    public static final Answer ANSWER_OF_SANJIGI = new Answer(SANJIGI, QUESTION, "Answers Contents");

    @Nested
    @DisplayName("deleteByUser() 테스트")
    class DeleteByUserTest {
        @Test
        @DisplayName("user와 Answer.writer가 일치하지 않는 경우 CannotDeleteException이 발생한다.")
        void testFailCase() {
            NsUser user = SANJIGI;
            Answer answer = new Answer(JAVAJIGI, QUESTION, "Answers Contents1");

            assertThatThrownBy(() -> answer.deleteByUser(user))
                    .isExactlyInstanceOf(CannotDeleteException.class)
                    .hasMessage(NO_AUTHORITY_TO_DELETE_ANSWER.message());
        }

        @Test
        @DisplayName("삭제가 진행되는 경우 Answer.deleted가 true로 변하며 DeleteHistory를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = JAVAJIGI;
            Answer answer = new Answer(JAVAJIGI, QUESTION, "Answers Contents");
            DeleteHistory expectedDeleteHistory = DeleteHistory.answerOf(answer.getId(), answer.getWriter());

            DeleteHistory deleteHistory = answer.deleteByUser(user);

            assertThat(answer.isDeleted()).isTrue();
            assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
        }
    }
}
