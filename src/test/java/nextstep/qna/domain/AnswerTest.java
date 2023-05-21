package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("답변 삭제시 히스토리체크")
    void delete_answer_test() throws CannotDeleteException {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, A1.getId(), A1.getWriter(), LocalDateTime.now());

        assertThat(A1.delete(NsUserTest.JAVAJIGI)).isEqualTo(deleteHistory);
    }

    @Test
    @DisplayName("다른사람이 삭제시 exception 테스트")
    void exception_not_owner_try_delete_test(){
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

}
