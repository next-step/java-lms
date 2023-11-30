package nextstep.qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

public class AnswerTest {

    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1,
        "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1,
        "Answers Contents2");

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> A1.deleteBy(NsUserTest.SANJIGI)).isInstanceOf(
            CannotDeleteException.class);
    }

    @Test
    public void delete_성공() throws Exception {
        // when
        A1.deleteBy(NsUserTest.JAVAJIGI);

        // then
        assertThat(A1.isDeleted()).isTrue();
    }
}
