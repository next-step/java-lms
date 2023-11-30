package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class QuestionTest {
    public static final Question Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
    public static final Question Q2 = new Question(NsUserTest.SANJIGI, "title2", "contents2");

    @Test
    void delete_다른_사람이_쓴_글() {
        assertThrows(CannotDeleteException.class, () -> Q1.delete(NsUserTest.SANJIGI));
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        Q1.delete(NsUserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() throws CannotDeleteException {
        Q1.addAnswer(new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents1"));

        assertThrows(CannotDeleteException.class, () -> Q1.delete(NsUserTest.JAVAJIGI));
    }

    @Test
    void delete_성공_질문자_답변자_같음() throws CannotDeleteException {

        Q2.addAnswer(new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents1"));

        Q2.delete(NsUserTest.SANJIGI);
        assertThat(Q2.isDeleted()).isTrue();
    }
}
