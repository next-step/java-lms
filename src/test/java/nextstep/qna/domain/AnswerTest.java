package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    @Test
    @DisplayName("자기자신의 답변은 삭제할 수 있다")
    void deleteAnswer() {
        // given
        Answer a1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        a1.delete(NsUserTest.JAVAJIGI);

        // then
        assertThat(a1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("다른사람의 답변은 삭제할 수 없다")
    void deleteAnswerFail() {
        // given
        Answer a1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");

        // when
        Assertions.assertThatThrownBy(() -> a1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("자기 자신의 답변만 삭제할 수 있습니다.");
    }
}
