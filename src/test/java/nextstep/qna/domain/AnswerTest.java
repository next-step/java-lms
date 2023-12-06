package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
        "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
        "Answers Contents2");

    @Test
    @DisplayName("본인이 작성한 답변은 삭제할 수 있다")
    void delete_no_other_answer() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();

        A2.delete(NsUserTest.SANJIGI);
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("본인이 작성하지 않은 답변은 삭제할 수 없다")
    void delete_other_answer() {
        Assertions.assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class);

        Assertions.assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }

}
