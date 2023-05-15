package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @AfterEach
    void tearDown() {
        A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("답변 삭제")
    void delete() throws CannotDeleteException {
        // when
        A1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변 삭제 - 삭제된 답변 예외")
    void deleteException() throws CannotDeleteException {
        // given
        A1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThatThrownBy(() -> A1.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("이미 삭제된 답변입니다.");
    }

    @Test
    @DisplayName("답변 삭제 - 요청자와 답변자가 다른 경우")
    void deleteException2() throws CannotDeleteException {
        // then
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("답변을 삭제할 권한이 없습니다.");
    }

}
