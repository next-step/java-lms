package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    public void shouldThrowException_whenWriterAndUserIsDifferent() throws Exception {
        //given
        NsUser loginUser = NsUserTest.SANJIGI;
        //when & then
        assertThatThrownBy(() -> {
            A1.isNotByUser(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void answerIsDeleted_whenCallDelete() {
        A1.delete();
        assertThat(A1.isDeleted()).isTrue();
    }
}
