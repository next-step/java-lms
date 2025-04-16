package nextstep.qna.domain;

import static nextstep.qna.CommonTestFixture.*;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import nextstep.users.domain.NsUserTest;

public class DeleteHistoryTest {
    public Answer answer1;

    @BeforeEach
    void setUp() {
        answer1 = A1;
    }

    @Test
    void deleteAnswerTest() {
        DeleteHistory deleteHistory = new DeleteHistory(ContentType.ANSWER, answer1.getId(), answer1.getWriter(),
            LocalDateTime.now());
        DeleteHistory history = answer1.deleteBy(NsUserTest.JAVAJIGI);
        assertEquals(deleteHistory, history);
    }
}
