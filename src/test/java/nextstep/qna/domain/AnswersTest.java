package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.ANSWER_OF_JAVAJIGI;
import static nextstep.qna.domain.AnswerTest.ANSWER_OF_SANJIGI;
import static nextstep.qna.domain.QuestionTest.QUESTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.ZIPJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    @Nested
    @DisplayName("deleteByUser() 테스트")
    class DeleteByUserTest {
        @Test
        @DisplayName("user가 answers의 writer와 모두 일치하지 않는 다면 CannotDeleteException이 발생한다.")
        void testFailCase() {
            Answers answers = new Answers(List.of(ANSWER_OF_JAVAJIGI, ANSWER_OF_SANJIGI));
            assertThatThrownBy(() -> answers.deleteByUser(ZIPJIGI)).isExactlyInstanceOf(CannotDeleteException.class);
        }

        @Test
        @DisplayName("삭제를 성공한 경우 List<DeleteHistory>를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = JAVAJIGI;
            Answer answer = new Answer(JAVAJIGI, QUESTION, "Answers Contents");
            Answers answers = new Answers(List.of(answer));
            DeleteHistory expectedDeleteHistory = DeleteHistory.answerOf(answer.getId(), answer.getWriter());

            List<DeleteHistory> deleteHistories = answers.deleteByUser(user);

            assertThat(deleteHistories.get(0)).isEqualTo(expectedDeleteHistory);
        }
    }
}
