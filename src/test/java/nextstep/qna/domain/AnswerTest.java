package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.qna.domain.QuestionTest.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

    public static final Answer A3 = new Answer(1L,NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
    public static final Answer A4 = new Answer(2L,NsUserTest.SANJIGI, Q1, "Answers Contents2");

    @Test
    @DisplayName("질문자와 답변자가 다른 경우 답변을 삭제 할 수없다.")
    void 질문자와_답변자가_다르면_예외반환(){
        assertThatThrownBy(() -> A1.validateAnswer(A2.getWriter()))
                .isInstanceOf(CannotDeleteException.class);

    }

    @Test
    @DisplayName("답변의 삭제 상태를 변경한다.")
    void 답변_삭제_상태_변경(){
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }

}
