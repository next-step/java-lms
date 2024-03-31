package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static nextstep.qna.domain.Answer.NO_DELITION_RIGHT;
import static nextstep.qna.domain.LocalDateTimeProviderTestModel.CREATED_DATE_TIME;
import static nextstep.qna.domain.LocalDateTimeProviderTestModel.LOCAL_DATE_TIME_PROVIDER;
import static nextstep.qna.domain.Question.NO_AUTHORITY_TO_DELETE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    @DisplayName("질문 작성자와 답변 작성자들이 모두 같아서 성공적으로 질문 삭제")
    void testQuestion_Delete() throws CannotDeleteException {
        // given
        Q1.addAnswer(AnswerTest.A1);

        // when
        List<DeleteHistory> deleteHistories = Q1.delete(NsUserTest.JAVAJIGI, LOCAL_DATE_TIME_PROVIDER.now());
        DeleteHistory questionDeleteHistory = deleteHistories.get(0);
        DeleteHistory answerDeleteHistory = deleteHistories.get(1);

        // then
        assertThat(Q1.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(2);

        assertThat(questionDeleteHistory.getCreatedDate()).isEqualTo(CREATED_DATE_TIME);
        assertThat(questionDeleteHistory.getDeletedBy()).isEqualTo(NsUserTest.JAVAJIGI);
        assertThat(questionDeleteHistory.getContentType()).isEqualTo(ContentType.QUESTION);

        assertThat(answerDeleteHistory.getCreatedDate()).isEqualTo(CREATED_DATE_TIME);
        assertThat(answerDeleteHistory.getDeletedBy()).isEqualTo(NsUserTest.JAVAJIGI);
        assertThat(answerDeleteHistory.getContentType()).isEqualTo(ContentType.ANSWER);
    }

    @Test
    @DisplayName("질문 작성자와 답변 작성자가 1개라도 달라서 질문 삭제 실패")
    void testQuestion_Delete_ShouldThrowException() throws CannotDeleteException {
        // given
        Q2.addAnswer(AnswerTest.A1);
        Q2.addAnswer(AnswerTest.A2);

        // given, when, then
        assertThatThrownBy(() -> Q2.delete(NsUserTest.SANJIGI, LOCAL_DATE_TIME_PROVIDER.now()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(NO_DELITION_RIGHT);
    }

    @Test
    @DisplayName("글 작성자와 삭제 요청자가 달라서 예외 발생")
    void testQuestion_Delete_DeleteRequestIdNotEqualQuestionId_ShouldThrowException() {
        // given, when, then
        assertThatThrownBy(() -> Q1.delete(NsUserTest.SANJIGI, LOCAL_DATE_TIME_PROVIDER.now()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(NO_AUTHORITY_TO_DELETE);
    }
}
