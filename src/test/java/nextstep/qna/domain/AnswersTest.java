package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.AnswerTest.answerOfJAVAJIGI;
import static nextstep.qna.domain.AnswerTest.answerOfSANJIGI;
import static nextstep.qna.domain.QuestionTest.QUESTION;
import static nextstep.users.domain.NsUserTest.JAVAJIGI;
import static nextstep.users.domain.NsUserTest.SANJIGI;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {
    @Test
    @DisplayName("writerOfQuestion와 모든 Answer.writer가 같은 경우 true를 그렇지 않으면 false를 반환한다.")
    void testIsDeletableByUser() {
        NsUser writerOfQuestion = SANJIGI;
        Answers answers = new Answers(List.of(answerOfJAVAJIGI, answerOfSANJIGI));

        assertThat(answers.isDeletableByWriter(writerOfQuestion)).isFalse();
    }

    @Nested
    @DisplayName("deleteByUser() 테스트")
    class DeleteByUserTest {
        @Test
        @DisplayName("user와 Answer.writer가 일치하지 않는 경우 CannotDeleteException라는 예외가 발생한다.")
        void testFailCase() {
            NsUser user = SANJIGI;
            Answers answers = new Answers(List.of(answerOfJAVAJIGI));

            assertThatThrownBy(() -> answers.deleteByUser(user)).isExactlyInstanceOf(CannotDeleteException.class);
        }

        @Test
        @DisplayName("삭제를 성공한 경우 List<DeleteHistory>를 반환한다.")
        void testSuccessCase() throws CannotDeleteException {
            NsUser user = JAVAJIGI;
            Answer answer = new Answer(JAVAJIGI, QUESTION, "Answers Contents");
            Answers answers = new Answers(List.of(answer));
            DeleteHistory expectedDeleteHistory = DeleteHistory.answerDeleteHistory(answer.getId(), answer.getWriter());

            List<DeleteHistory> deleteHistories = answers.deleteByUser(user);

            assertThat(deleteHistories.get(0)).isEqualTo(expectedDeleteHistory);
        }
    }
}
