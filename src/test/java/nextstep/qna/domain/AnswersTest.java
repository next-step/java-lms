package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.qna.domain.Answer.NO_DELITION_RIGHT;
import static nextstep.qna.domain.AnswerTest.A1;
import static nextstep.qna.domain.AnswerTest.A2;
import static nextstep.qna.domain.LocalDateTimeProviderTestModel.LOCAL_DATE_TIME_PROVIDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    @DisplayName("답변 작성자와 삭제 요청자가 동일하므로 모든 답변을 정상 삭제")
    void testAnswers_Delete() throws CannotDeleteException {
        // given
        Answers answers = new Answers(List.of(A1));

        // when
        List<DeleteHistory> deleteHistories = answers.delete(NsUserTest.JAVAJIGI, LOCAL_DATE_TIME_PROVIDER.now());

        // then
        assertThat(deleteHistories).hasSize(1);
    }

    @Test
    @DisplayName("답변 작성자와 삭제 요청자가 1개라도 달라서 에러 발생")
    void testAnswers_Delete_DeleteRequestIdNotEqualAnswerId_ShouldThrowException() {
        // given
        Answers answers = new Answers(List.of(A1, A2));

        // when, then
        assertThatThrownBy(() -> answers.delete(NsUserTest.JAVAJIGI, LOCAL_DATE_TIME_PROVIDER.now()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(NO_DELITION_RIGHT);
    }

    @Test
    @DisplayName("답변 작성자와 삭제 요청자가 모두 달라서 에러 발생")
    void testAnswers_Delete_AllDeleteRequestIdNotEqualAnswerId_ShouldThrowException() {
        // given
        Answers answers = new Answers(List.of(A1));

        // when, then
        assertThatThrownBy(() -> answers.delete(NsUserTest.SANJIGI, LOCAL_DATE_TIME_PROVIDER.now()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(NO_DELITION_RIGHT);
    }

}
