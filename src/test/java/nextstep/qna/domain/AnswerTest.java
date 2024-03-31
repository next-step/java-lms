package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.Answer.NO_DELITION_RIGHT;
import static nextstep.qna.domain.LocalDateTimeProviderTestModel.CREATED_DATE_TIME;
import static nextstep.qna.domain.LocalDateTimeProviderTestModel.LOCAL_DATE_TIME_PROVIDER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 작성자와 삭제 요청자가 동일하므로 정상 삭제")
    void testAnswer_Delete() throws CannotDeleteException {
        // given, when
        DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI, LOCAL_DATE_TIME_PROVIDER.now());

        // then
        assertThat(A1.isDeleted()).isTrue();
        assertThat(deleteHistory.getContentId()).isEqualTo(null);
        assertThat(deleteHistory.getDeletedBy()).isEqualTo(NsUserTest.JAVAJIGI);
        assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.ANSWER);
        assertThat(deleteHistory.getCreatedDate()).isEqualTo(CREATED_DATE_TIME);
    }

    @Test
    @DisplayName("답변 작성자와 삭제 요청자가 달라서 에러 발생")
    void testAnswer_Delete_DeleteRequestIdNotEqualAnswerId_ShouldThrowException() {
        // given, when, then
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI, LOCAL_DATE_TIME_PROVIDER.now()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessageContaining(NO_DELITION_RIGHT);
    }

}
