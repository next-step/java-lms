package nextstep.qna.domain;

import nextstep.qna.UnAuthorizedException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class AnswerTest {

    @Test
    public void delete_success() {
        // given
        Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1" );
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

        // when
        A1.delete(NsUserTest.JAVAJIGI);
        A2.delete(NsUserTest.SANJIGI);

        // then
        assertThat(A1.isDeleted()).isTrue();
        assertThat(A2.isDeleted()).isTrue();
    }

    @Test
    public void delete_fail() {
        // given
        Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1" );
        Answer A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        Answer A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");

        // then
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("답변의 작성자가 아닙니다.");

        assertThatThrownBy(() -> A2.delete(NsUserTest.JAVAJIGI))
                .isInstanceOf(UnAuthorizedException.class)
                .hasMessage("답변의 작성자가 아닙니다.");
    }
}
