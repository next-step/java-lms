package nextstep.qna.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AnswerTest {
    private Question Q1;
    private Answer A1;
    private Answer A2;

    @BeforeEach
    void setUp() {
        Q1 = new Question(NsUserTest.JAVAJIGI, "title1", "contents1");
        A1 = new Answer(NsUserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(NsUserTest.SANJIGI, Q1, "Answers Contents2");
    }

    @Test
    public void delete_성공() throws Exception {
        //given, when
        DeleteHistory history = A1.delete();

        //then
        assertTrue(A1.isDeleted());
        assertNotNull(history);
    }
}
