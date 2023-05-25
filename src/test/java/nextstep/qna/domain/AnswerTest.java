package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    public static final Answer A1 = new Answer(NsUserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(NsUserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");


    @Test
    public void delete_성공() throws Exception {
        Answer answer = ofUser(11L, NsUserTest.JAVAJIGI);
        answer.delete(NsUserTest.JAVAJIGI);

        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람_답변() {
        Answer answer = ofUser(11L, NsUserTest.JAVAJIGI);
        assertThatThrownBy(() -> {
            answer.delete(NsUserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    public static Answer ofUser(long id, NsUser user) {
        return new Answer(id, user, QuestionTest.Q1, "Answers Contents");
    }
}
