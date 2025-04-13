package nextstep.qna.domain;

import nextstep.qna.CannotDeleteException;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {
    Answer A1;
    Answer A2;
    Question Q1;
    @BeforeEach
    public void init() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("delete를 통해 answer은 삭제 상태가 된다.")
    public void delete() throws CannotDeleteException {
        A1.delete(NsUserTest.JAVAJIGI);
        assertThat(A1.isDeleted()).isTrue();
    }
}
