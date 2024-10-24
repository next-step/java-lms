package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("삭제 권한 없음")
    @Test
    void 삭제_권한x(){
        assertThatThrownBy(() -> {
            A1.validateDeleteQuestionAnswer(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("질문 삭제")
    @Test
    void 질문_삭제(){
        A1.deleteAnswer();
        assertThat(A1.isDeleted()).isTrue();
    }

}
