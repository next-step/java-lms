package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    public void delete_success() {
        // when
        A1.delete(NsUserTest.JAVAJIGI);
        A2.delete(NsUserTest.SANJIGI);

        // then
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    public void delete_fail() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("답변의 작성자가 아닙니다.");

        assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("답변의 작성자가 아닙니다.");
    }
}
