package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {

    public Answer A1;
    public Answer A2;
    public Question Q1;

    @BeforeEach
    public void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @Test
    public void delete() throws Exception {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른사람이쓴글() {
        assertThatThrownBy(() -> A1.delete(NsUserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void getDeleteHistory() throws Exception {
        DeleteHistory deleteHistory = A1.delete(NsUserTest.JAVAJIGI).get(0);
        assertThat(deleteHistory).isEqualTo(new DeleteHistory(ContentType.ANSWER, null, NsUserTest.JAVAJIGI, LocalDateTime.now()));
    }
}
