package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("질문 작성자 외에 다른 사람이 답변한 것인지 확인.")
    void isOwner() {
        Assertions.assertThat(A1.isOwner(NsUserTest.JAVAJIGI)).isTrue();
        Assertions.assertThat(A1.isOwner(NsUserTest.SANJIGI)).isFalse();
    }

    @Test
    @DisplayName("질문 작성자 외에 다른 사람이 답변했을 경우 예외를 던진다.")
    void throwIfIsNotOwner() {

        Assertions.assertThatThrownBy(() ->
                A1.hasOtherUserAnswer(false)
        ).isInstanceOf(CannotDeleteException.class);
    }
}
