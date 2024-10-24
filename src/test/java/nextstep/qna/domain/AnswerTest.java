package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    private Answer A1;
    private Answer A2;

    @BeforeEach
    public void setUp() throws Exception {
        A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    }

    @Test
    @DisplayName("다른 사람의 답변이 있는글을 삭제하려고 하면 예외가 발생한다")
    void 다른_사람이_쓴_답변일_경우_삭제_불가() {
        assertThatThrownBy(() ->A1.validEachAnswerWrittenByMe(A2.getWriter()))
                .isInstanceOf(CannotDeleteException.class)
                .hasMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }

    @Test
    @DisplayName("내가 쓴 답변을 삭제하면 Answer의 상태가 삭제상태로 바뀐다")
    void 내가_쓴_답변_삭제_후_상태는_true () throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

}
