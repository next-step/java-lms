package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static nextstep.qna.ExceptionMessage.NO_AUTHORITY_TO_DELETE_ANSWER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer answerByJAVAJIGI = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer answerBySANJIGI = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Nested
    @DisplayName("deleteByUser() 테스트")
    class DeleteByUserTest {
        Answer answer;

        @BeforeEach
        void initAnswerOfDeleteByUserTest() {
            answer = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents");
        }

        @Test
        @DisplayName("user와 Answer.writer가 일치하지 않는 경우 CannotDeleteException이 발생한다.")
        void testFailCase() {
            NsUser user = NsUserTest.SANJIGI;
            assertThatThrownBy(() -> answer.deleteByUser(user))
                    .isExactlyInstanceOf(CannotDeleteException.class)
                    .hasMessage(NO_AUTHORITY_TO_DELETE_ANSWER.message());
        }

        @Test
        @DisplayName("삭제가 진행되는 경우 Answer.deleted가 true로 변하며 DeleteHistory를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = NsUserTest.JAVAJIGI;
            DeleteHistory expectedDeleteHistory = new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now());

            DeleteHistory deleteHistory = answer.deleteByUser(user);

            assertThat(answer.isDeleted()).isTrue();
            assertThat(deleteHistory).isEqualTo(expectedDeleteHistory);
        }
    }
}
