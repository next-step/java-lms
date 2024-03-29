package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("성공적으로 삭제된다")
    void delete() {
        assertThatNoException()
            .isThrownBy(() -> A1.delete(NsUserTest.JAVAJIGI));
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌데 삭제하려하면 예외가 발생한다")
    void delete_fail_for_not_owner() {
        assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class);
    }
}
